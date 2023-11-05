package xin.altitude.cms.plus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.LambdaUtils;
import com.baomidou.mybatisplus.core.toolkit.ReflectionKit;
import com.baomidou.mybatisplus.core.toolkit.support.LambdaMeta;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.reflection.property.PropertyNamer;
import org.springframework.transaction.annotation.Transactional;
import xin.altitude.cms.common.constant.RedisConstants;
import xin.altitude.cms.common.util.EntityUtils;
import xin.altitude.cms.common.util.RedisUtils;
import xin.altitude.cms.common.util.SpringUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * {@link BaseMapper}增强类 增强DAO处理能力
 *
 * @author <a href="http://www.altitude.xin" target="_blank">赛泰先生</a>
 * @author <a href="https://gitee.com/decsa/ucode-cms-vue" target="_blank">UCode CMS</a>
 * @author <a href="https://space.bilibili.com/1936685014" target="_blank">B站视频</a>
 **/
public interface IBaseMapper<T> extends BaseMapper<T> {

    /**
     * 获取泛型实体类
     * <p>
     * 此方法为帮助方法 非dao方法
     *
     * @return Class 对象
     */
    @SuppressWarnings("unchecked")
    default Class<T> getEntityClass() {
        return (Class<T>) ReflectionKit.getSuperClassGenericType(this.getClass(), IBaseMapper.class, 1);
    }

    /**
     * 【新增方法】主键查询（添加缓存）
     *
     * @param id       主键ID
     * @param timeout  过期时间（小于或者等于0 表示缓存不过期）
     * @param timeUnit 时间单位
     * @return T实例
     */
    default T selectById(Serializable id, long timeout, TimeUnit timeUnit) {
        Class<T> clazz = getEntityClass();
        T t = RedisUtils.getObject(id, clazz);
        if (t == null) {
            synchronized (RedisConstants.createLockKey(clazz, id)) {
                T t2 = RedisUtils.getObject(id, clazz);
                if (t2 == null) {
                    T t3 = this.selectById(id);
                    RedisUtils.save(id, t3, timeout, timeUnit);
                    return t3;
                }
                return t2;
            }
        }
        return t;
    }

    /**
     * 【新增方法】批量保存（添加缓存）
     *
     * @param idList   主键ID集合
     * @param timeout  过期时间（小于或者等于0 表示缓存不过期）
     * @param timeUnit 时间单位
     * @return 集合实例
     */
    default List<T> selectBatchIds(Collection<Serializable> idList, long timeout, TimeUnit timeUnit) {
        Class<T> clazz = getEntityClass();
        List<T> tList = RedisUtils.getObjects(idList, clazz);
        List<T> result = new ArrayList<>(idList.size());
        Set<Serializable> ids = new HashSet<>();
        TableInfo tableInfo = TableInfoHelper.getTableInfo(clazz);
        for (T t : tList) {
            if (t != null) {
                ids.add((Serializable) tableInfo.getPropertyValue(t, tableInfo.getKeyProperty()));
                result.add(t);
            }
        }
        List<Serializable> remainIds = idList.stream().filter(e -> !ids.contains(e)).collect(Collectors.toList());
        if (remainIds.size() > 0) {
            Map<Serializable, T> remainMap = this.selectMaps(remainIds);
            for (Map.Entry<Serializable, T> entry : remainMap.entrySet()) {
                result.add(entry.getValue());
                RedisUtils.save(entry.getKey(), entry.getValue(), timeout, timeUnit);
            }
        }
        return result;
    }

    /**
     * 【新增方法】自增
     *
     * <pre>
     *     // 对学生表中主键为【1】的用户的【age】字段进行自增【1】
     *     studentMapper.incr(1L, "age", 1);
     * </pre>
     *
     * @param id    主键ID
     * @param field 需要自增的字段名
     * @param step  步长 步长需要大于0 否则会出现相反的业务逻辑
     * @return 如果更新成功返回1 否则返回0
     */
    int incr(@Param("pkVal") Serializable id, @Param("field") String field, @Param("step") int step);

    /**
     * 【新增方法】自增
     *
     * @param id    主键ID
     * @param field 需要自增的字段名
     * @param step  步长 步长需要大于0 否则会出现相反的业务逻辑
     * @return 如果更新成功返回1 否则返回0
     */
    int decr(@Param("pkVal") Serializable id, @Param("field") String field, @Param("step") int step);


    /**
     * 【新增方法】将主键批量结果由List转化成Map结构
     *
     * @param idList 主键集合
     * @return Map实例 其中Key为主键ID Value为当前实例
     */
    default Map<Serializable, T> selectMaps(Collection<? extends Serializable> idList) {
        List<T> tList = this.selectBatchIds(idList);
        Map<Serializable, T> map = new HashMap<>(idList.size());
        Class<T> clazz = getEntityClass();
        TableInfo tableInfo = TableInfoHelper.getTableInfo(clazz);
        for (T t : tList) {
            Serializable pkVal = (Serializable) tableInfo.getPropertyValue(t, tableInfo.getKeyProperty());
            map.put(pkVal, t);
        }
        return map;
    }


    /**
     * <p>【新增方法】保存数据</p>
     * <p>
     * 保存数据 同时更新因当前表数据增加 而改变表的统计列
     *
     * @param entity     DO实体类实例
     * @param fkColumn   DO实体类关联外键列（方法引用表示）
     * @param mapper     外键表对应的Mapper类的实例
     * @param statColumn 统计更新字段（方法引用表示）
     * @param <D>        外键对应实体类泛型
     * @return 保存成功 返回true
     * @since 1.6.4
     */
    @Transactional(rollbackFor = Exception.class)
    default <D> boolean insert(T entity, SFunction<T, ? extends Serializable> fkColumn, IBaseMapper<D> mapper, SFunction<D, ?> statColumn) {
        Serializable fkValue = EntityUtils.toObj(entity, fkColumn);
        Class<T> clazz = getEntityClass();
        synchronized (clazz) {
            boolean rSave = insert(entity) > 0;
            String field = Optional.ofNullable(statColumn).map(LambdaUtils::extract)
                .map(LambdaMeta::getImplMethodName)
                .map(PropertyNamer::methodToProperty).orElse(null);
            boolean rUpdate = mapper.incr(fkValue, field, 1) > 0;
            return rSave && rUpdate;
        }
    }

    /**
     * <p>【新增方法】保存数据</p>
     * <p>
     * 保存数据 同时更新因当前表数据增加 而改变表的统计列
     *
     * @param entity      DO实体类实例
     * @param fkColumn    DO实体类关联外键列（方法引用表示）
     * @param mapperClazz 外键表对应的Mapper类的Class对象
     * @param statColumn  统计更新字段（方法引用表示）
     * @param <D>         外键对应实体类泛型
     * @return 保存成功 返回true
     * @since 1.6.4
     */
    @Transactional(rollbackFor = Exception.class)
    default <D> boolean insert(T entity, SFunction<T, ? extends Serializable> fkColumn, Class<IBaseMapper<D>> mapperClazz, SFunction<D, ?> statColumn) {
        return insert(entity, fkColumn, SpringUtils.getBean(mapperClazz), statColumn);
    }


    /**
     * <p>【新增方法】删除数据</p>
     *
     * @param id          主键ID
     * @param fkValue     DO实体类关联外键值
     * @param mapperClazz 外键表对应的Mapper类的Class对象
     * @param statColumn  统计更新字段（方法引用表示）
     * @param <D>         外键对应实体类泛型
     * @return 删除成功 返回true
     * @since 1.6.4
     */
    @Transactional(rollbackFor = Exception.class)
    default <D> boolean deleteById(Serializable id, Serializable fkValue, Class<IBaseMapper<D>> mapperClazz, SFunction<D, ?> statColumn) {
        return deleteById(id, fkValue, SpringUtils.getBean(mapperClazz), statColumn);
    }


    /**
     * <p>【新增方法】删除数据</p>
     *
     * @param id         主键ID
     * @param fkValue    DO实体类关联外键值
     * @param mapper     外键表对应的Mapper类的实例
     * @param statColumn 统计更新字段（方法引用表示）
     * @param <D>        外键对应实体类泛型
     * @return 删除成功 返回true
     * @since 1.6.4
     */
    @Transactional(rollbackFor = Exception.class)
    default <D> boolean deleteById(Serializable id, Serializable fkValue, IBaseMapper<D> mapper, SFunction<D, ?> statColumn) {
        Class<T> clazz = getEntityClass();
        synchronized (clazz) {
            boolean rDelete = deleteById(id) > 0;
            String field = Optional.ofNullable(statColumn).map(LambdaUtils::extract)
                .map(LambdaMeta::getImplMethodName)
                .map(PropertyNamer::methodToProperty).orElse(null);
            boolean rUpdate = mapper.decr(fkValue, field, 1) > 0;
            return rDelete && rUpdate;
        }
    }

}

package xin.altitude.cms.plus.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfoHelper;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;
import xin.altitude.cms.common.util.EntityUtils;
import xin.altitude.cms.common.util.GuavaUtils;
import xin.altitude.cms.common.util.RefUtils;
import xin.altitude.cms.common.util.SpringUtils;
import xin.altitude.cms.plus.support.CustomUpdateWrapper;
import xin.altitude.cms.plus.util.PlusUtils;

import java.io.Serializable;
import java.util.Objects;
import java.util.function.Function;

/**
 * 增强{@link IService}类 丰富功能
 *
 * @author <a href="http://www.altitude.xin" target="_blank">赛泰先生</a>
 * @author <a href="https://gitee.com/decsa/ucode-cms-vue" target="_blank">UCode CMS</a>
 * @author <a href="https://space.bilibili.com/1936685014" target="_blank">B站视频</a>
 **/
public interface IBaseService<T> extends IService<T> {

    /**
     * <p>保存数据</p>
     *
     * @param entity       DO实体类实例
     * @param fkColumn     DO实体类关联外键列（方法引用表示）
     * @param serviceClazz 外键表对应的服务类的Class对象 需要实现{@link IBaseService}接口
     * @param statColumn   统计更新字段（方法引用表示）
     * @param <D>          外键对应实体类泛型
     * @return 保存成功 返回true
     * @since 1.6.4
     */
    default <D> boolean save(T entity, SFunction<T, ? extends Serializable> fkColumn, Class<? extends IBaseService<D>> serviceClazz, SFunction<D, ?> statColumn) {
        IBaseService<D> service = SpringUtils.getBean(serviceClazz);
        return save(entity, fkColumn, service, statColumn);
    }

    /**
     * <p>保存数据</p>
     *
     * @param entity     DO实体类实例
     * @param fkColumn   DO实体类关联外键列（方法引用表示）
     * @param service    外键表对应的服务类的实例
     * @param statColumn 统计更新字段（方法引用表示）
     * @param <D>        外键对应实体类泛型
     * @return 保存成功 返回true
     * @since 1.6.4
     */
    @Transactional(rollbackFor = Exception.class)
    default <D> boolean save(T entity, SFunction<T, ? extends Serializable> fkColumn, IBaseService<D> service, SFunction<D, ?> statColumn) {
        Serializable fkValue = EntityUtils.toObj(entity, fkColumn);

        synchronized (getEntityClass()) {
            boolean rSave = save(entity);
            Long count = count(new LambdaUpdateWrapper<T>().eq(fkColumn, fkValue));
            boolean rUpdate = service.updateById(fkValue, statColumn, count);
            return rSave && rUpdate;
        }
    }

    /**
     * <p>删除数据</p>
     *
     * @param entity       DO实体类实例
     * @param fkColumn     DO实体类关联外键列（方法引用表示）
     * @param serviceClazz 外键表对应的服务类的Class对象 需要实现{@link IBaseService}接口
     * @param statColumn   统计更新字段（方法引用表示）
     * @param <D>          外键对应实体类泛型
     * @return 删除成功 返回true
     * @since 1.6.4
     */
    default <D> boolean removeById(T entity, SFunction<T, ? extends Serializable> fkColumn, Class<? extends IBaseService<D>> serviceClazz, SFunction<D, ?> statColumn) {
        IBaseService<D> service = SpringUtils.getBean(serviceClazz);
        return removeById(entity, fkColumn, service, statColumn);
    }

    /**
     * <p>删除数据</p>
     *
     * @param entity     DO实体类实例
     * @param fkColumn   DO实体类关联外键列（方法引用表示）
     * @param service    外键表对应的服务类的实例
     * @param statColumn 统计更新字段（方法引用表示）
     * @param <D>        外键对应实体类泛型
     * @return 删除成功 返回true
     * @since 1.6.4
     */
    @Transactional(rollbackFor = Exception.class)
    default <D> boolean removeById(T entity, SFunction<T, ? extends Serializable> fkColumn, IBaseService<D> service, SFunction<D, ?> statColumn) {
        Serializable fkValue;
        Serializable fkValueTmp = EntityUtils.toObj(entity, fkColumn);
        // 如果关联外键为null 则从数据库中重新查出来 保证能够正确更新统计字段
        if (Objects.isNull(fkValueTmp)) {
            Serializable pkVal = PlusUtils.pkVal(entity, getEntityClass());
            fkValue = EntityUtils.toObj(getById(pkVal), fkColumn);
        } else {
            fkValue = fkValueTmp;
        }

        synchronized (getEntityClass()) {
            boolean result = removeById(entity);
            Long count = count(new LambdaUpdateWrapper<T>().eq(fkColumn, fkValue));
            return result && service.updateById(fkValue, statColumn, count);
        }
    }

    /**
     * <p>删除数据</p>
     *
     * @param id           主键ID
     * @param fkColumn     DO实体类关联外键列（方法引用表示）
     * @param fkValue      DO实体类关联外键值
     * @param serviceClazz 外键表对应的服务类的Class对象 需要实现{@link IBaseService}接口
     * @param statColumn   统计更新字段（方法引用表示）
     * @param <D>          外键对应实体类泛型
     * @return 删除成功 返回true
     * @since 1.6.4
     */
    default <D> boolean removeById(Serializable id, SFunction<T, ?> fkColumn, Serializable fkValue, Class<? extends IBaseService<D>> serviceClazz, SFunction<D, ?> statColumn) {
        IBaseService<D> bean = SpringUtils.getBean(serviceClazz);
        return removeById(id, fkColumn, fkValue, bean, statColumn);
    }

    /**
     * <p>删除数据</p>
     *
     * @param id         主键ID
     * @param fkColumn   DO实体类关联外键列（方法引用表示）
     * @param fkValue    DO实体类关联外键值
     * @param service    外键表对应的服务类的实例
     * @param statColumn 统计更新字段（方法引用表示）
     * @param <D>        外键对应实体类泛型
     * @return 删除成功 返回true
     * @since 1.6.4
     */
    @Transactional(rollbackFor = Exception.class)
    default <D> boolean removeById(Serializable id, SFunction<T, ?> fkColumn, Serializable fkValue, IBaseService<D> service, SFunction<D, ?> statColumn) {
        synchronized (getEntityClass()) {
            boolean result = removeById(id);
            Long count = count(new LambdaUpdateWrapper<T>().eq(fkColumn, fkValue));
            return result && service.updateById(fkValue, statColumn, count);
        }
    }

    /**
     * <p>通过主键ID来更新</p>
     * <p>待更新的值通过计算产生</p>
     *
     * @param id     主键ID
     * @param field  DO实体类属性字段
     * @param action 回调方法 获取即将更新的Value值
     * @return 如果更新成功 返回true
     * @since 1.6.4
     */
    default boolean updateById(Serializable id, SFunction<T, ?> field, Function<Serializable, ? extends Number> action) {
        String filedName = RefUtils.getFiledName(field);
        String columnName = GuavaUtils.toUnderScoreCase(filedName);
        return updateById(id, columnName, action);
    }

    /**
     * <p>通过主键ID来更新</p>
     * <p>待更新的值通过计算产生</p>
     *
     * @param id    主键ID
     * @param field DO实体类属性字段
     * @param value 即将更新的Value值
     * @return 如果更新成功 返回true
     * @since 1.6.4
     */
    default boolean updateById(Serializable id, SFunction<T, ?> field, Serializable value) {
        String filedName = RefUtils.getFiledName(field);
        String columnName = GuavaUtils.toUnderScoreCase(filedName);
        return updateById(id, columnName, value);
    }

    /**
     * <p>通过主键ID来更新</p>
     * <p>待更新的值通过计算产生</p>
     *
     * @param id     主键ID
     * @param column 数据库列字段
     * @param action 回调方法 获取即将更新的Value值
     * @return 如果更新成功 返回true
     * @since 1.6.4
     */
    default boolean updateById(Serializable id, String column, Function<Serializable, ? extends Number> action) {
        Number value = action.apply(id);
        return updateById(id, column, value);
    }

    /**
     * <p>通过主键ID来更新</p>
     * <p>待更新的值通过计算产生</p>
     *
     * @param id     主键ID
     * @param column 数据库列字段
     * @param value  即将更新的Value值
     * @return 如果更新成功 返回true
     * @since 1.6.4
     */
    default boolean updateById(Serializable id, String column, Serializable value) {
        TableInfo tableInfo = TableInfoHelper.getTableInfo(getEntityClass());

        UpdateWrapper<T> wrapper = new UpdateWrapper<T>()
            .set(column, value).eq(tableInfo.getKeyColumn(), id);
        return update(wrapper);
    }


    /**
     * <p>通过主键ID查询</p>
     * <p>冗余查询条件</p>
     *
     * @param id    主键ID
     * @param field DO实体类属性字段 通常是逻辑外键列
     * @param value field列对应的查询值
     * @return DO实例
     * @since 1.6.4
     */
    default T getById(Serializable id, SFunction<T, ?> field, Object value) {
        String filedName = RefUtils.getFiledName(field);
        String columnName = GuavaUtils.toUnderScoreCase(filedName);
        TableInfo tableInfo = TableInfoHelper.getTableInfo(getEntityClass());
        QueryWrapper<T> wrapper = new QueryWrapper<T>().eq(columnName, value).eq(tableInfo.getKeyColumn(), id);
        return getOne(wrapper);
    }

    /**
     * <p>通过主键ID自增指定列</p>
     * <p>默认步长为1</p>
     *
     * @param id    主键ID
     * @param field DO实体类属性字段
     * @return 更新成功 返回true
     * @since 1.6.4
     */
    default boolean increaseById(Serializable id, SFunction<T, ? extends Number> field) {
        return increaseById(id, field, 1);
    }

    /**
     * <p>通过主键ID自增指定列</p>
     * <p>自定义步长</p>
     *
     * @param id    主键ID
     * @param field DO实体类属性字段
     * @param step  步长
     * @return 更新成功 返回true
     * @since 1.6.4
     */
    default boolean increaseById(Serializable id, SFunction<T, ? extends Number> field, int step) {
        CustomUpdateWrapper<T> wrapper = new CustomUpdateWrapper<T>();
        TableInfo tableInfo = TableInfoHelper.getTableInfo(getEntityClass());
        String filedName = RefUtils.getFiledName(field);
        String columnName = GuavaUtils.toUnderScoreCase(filedName);
        wrapper.incr(columnName, step).eq(tableInfo.getKeyColumn(), id);
        return this.update(wrapper);
    }

    /**
     * <p>通过主键ID自减指定列</p>
     * <p>默认步长为1</p>
     *
     * @param id    主键ID
     * @param field DO实体类属性字段
     * @return 更新成功 返回true
     * @since 1.6.4
     */
    default boolean decreaseById(Serializable id, SFunction<T, ? extends Number> field) {
        return decreaseById(id, field, 1);
    }

    /**
     * <p>通过主键ID自减指定列</p>
     * <p>自定义步长</p>
     *
     * @param id    主键ID
     * @param field DO实体类属性字段
     * @param step  步长
     * @return 更新成功 返回true
     * @since 1.6.4
     */
    default boolean decreaseById(Serializable id, SFunction<T, ? extends Number> field, int step) {
        CustomUpdateWrapper<T> wrapper = new CustomUpdateWrapper<T>();
        TableInfo tableInfo = TableInfoHelper.getTableInfo(getEntityClass());
        String filedName = RefUtils.getFiledName(field);
        String columnName = GuavaUtils.toUnderScoreCase(filedName);
        wrapper.decr(columnName, step).eq(tableInfo.getKeyColumn(), id);
        return this.update(wrapper);
    }
}

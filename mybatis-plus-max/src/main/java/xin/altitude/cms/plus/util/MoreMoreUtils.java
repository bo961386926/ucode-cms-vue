package xin.altitude.cms.plus.util;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.IService;
import com.google.common.collect.Table;
import xin.altitude.cms.common.util.ColUtils;
import xin.altitude.cms.common.util.EntityUtils;
import xin.altitude.cms.common.util.MapUtils;
import xin.altitude.cms.common.util.TableUtils;
import xin.altitude.cms.plus.lang.IEntry;
import xin.altitude.cms.plus.lang.IdValue;
import xin.altitude.cms.plus.lang.PkFieldValue;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;

import static xin.altitude.cms.common.util.SpringUtils.getBean;

/**
 * 多对多查询快捷操作工具类 简化属性注入操作
 * <p>
 * 分为两种情况：关系表中纯做关系；关系表中既有关系也有属性
 * <p>
 * 纯关系代表：用户与角色
 * <p>
 * 含有属性关系：学生与课程（关系表得包含成绩）
 * <p>
 * 一对一查询属性注入{@link OneOneUtils} | 一对多查询属性注入{@link OneMoreUtils} | 多对多查询属性注入{@link MoreMoreUtils}
 *
 * @author <a href="http://www.altitude.xin" target="_blank">Java知识图谱</a>
 * @author <a href="https://gitee.com/decsa/ucode-cms-vue" target="_blank">UCode CMS</a>
 * @author <a href="https://space.bilibili.com/1936685014" target="_blank">B站视频</a>
 * @since 1.6.4
 **/
public class MoreMoreUtils {
    private MoreMoreUtils() {
    }


    /**
     * 取出{@link Table}实例中指定<i>rowKey</i>对应的<i>columnKey</i>的集合
     *
     * @param table  {@link Table}实例
     * @param rowVal 行记录值
     * @param <R>    行类型
     * @param <C>    列列星
     * @return 集合
     */
    public static <R, C> Set<C> columnKeys(Table<R, C, ?> table, R rowVal) {
        Objects.requireNonNull(table);
        Objects.requireNonNull(rowVal);
        return table.row(rowVal).keySet();
    }


    /**
     * 批量取出Map中的值
     *
     * @param map map实例
     * @param <C> key的泛型
     * @param <V> value的泛型
     * @return value的泛型的集合
     */
    public static <V, R, C> List<V> getCollection(Map<C, V> map, Table<R, C, ?> table, R rowVal) {
        Set<C> keys = columnKeys(table, rowVal);
        return MapUtils.getCollection(map, keys);
    }


    public static <T, V, R, C> List<V> getCollection(Collection<T> list, Function<? super T, ? extends C> keyAction, Function<? super T, ? extends V> valueAction, Table<R, C, ?> table, R rowVal) {
        Map<C, V> map = EntityUtils.toMap(list, keyAction, valueAction);
        return getCollection(map, table, rowVal);
    }


    // ⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇ 关联表中含有关系 还有额外属性 ⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇


    /**
     * 多对多 列表查询场景 属性注入
     * <p>
     * 适用于关系表不仅做关联、还有实际属性的情形
     * <p>
     * 主表(Student)、关联表(StuSubRelation)、副表(Subject)
     * <p>
     * 关系表中存在实际意义的字段，比方说学生与课程关系表中存在分数这个属性字段
     *
     * @param voList    列表集合实例
     * @param rService  关联表服务类实例
     * @param rowAction 关联表与主表逻辑外键关联列（方法引用表示）
     * @param vService  副表服务类实例
     * @param ccbAction 副表DO转BO Action
     * @param <VO>      需实现{@link PkFieldValue}接口
     * @param <R>       与主表VO中主键相同的数据类型
     * @param <C>       与副表中主键相同的数据类型
     * @param <V>       关系表中额外有价值字段
     * @param <RC>      关联表DO实体类类型 需要实现{@link Table.Cell}接口
     * @param <CC>      副表DO实体类类型
     * @param <CCB>     副表BO类型 需实现{@link IdValue}接口
     */
    public static <VO extends PkFieldValue<R, CCB>, R, C extends Serializable, V, RC extends Table.Cell<R, C, V>, CC, CCB extends IdValue<C, V>> void injectField(List<VO> voList, IService<RC> rService, SFunction<RC, R> rowAction, IService<CC> vService, Function<CC, CCB> ccbAction) {
        if (ColUtils.isNotEmpty(voList)) {
            // 取出主表主键ID的集合
            Set<R> stuIds = EntityUtils.toSet(voList, PkFieldValue::pkVal);
            // 通过主键ID 批量从关系表中查询数据
            LambdaQueryWrapper<RC> relationWrapper = Wrappers.lambdaQuery(rService.getEntityClass()).in(rowAction, stuIds);
            List<RC> relationList = rService.getBaseMapper().selectList(relationWrapper);
            // 取出课程ID集合（去重）
            Set<C> subIds = EntityUtils.toSet(relationList, Table.Cell::getColumnKey);
            // 构造table实例Map的Map结构
            Table<R, C, V> table = TableUtils.createHashTable(relationList);
            if (ColUtils.isNotEmpty(subIds)) {
                List<CC> subjectList = vService.getBaseMapper().selectBatchIds(subIds);

                for (VO studentVo : voList) {
                    R r = EntityUtils.toObj(studentVo, PkFieldValue::pkVal);
                    List<CCB> ccbList = EntityUtils.toList(subjectList, ccbAction);
                    Map<C, CCB> map = EntityUtils.toMap(ccbList, (Function<CCB, C>) IdValue::pkVal);
                    List<CCB> subjectBos = MoreMoreUtils.getCollection(map, table, r);
                    // 给分数字段进行赋值
                    for (CCB subjectBo : subjectBos) {
                        C c = EntityUtils.toObj(subjectBo, IdValue::pkVal);
                        subjectBo.setValue(table.get(r, c));
                    }
                    studentVo.setList(subjectBos);
                }
            }
        }
    }

    /**
     * 多对多 列表查询场景 属性注入
     * <p>
     * 适用于关系表不仅做关联、还有实际属性的情形
     * <p>
     * 主表(Student)、关联表(StuSubRelation)、副表(Subject)
     * <p>
     * 关系表中存在实际意义的字段，比方说学生与课程关系表中存在分数这个属性字段
     *
     * @param voList        列表集合实例
     * @param rServiceClazz 关联表服务类Class对象
     * @param rowAction     关联表与主表逻辑外键关联列（方法引用表示）
     * @param vServiceClazz 副表服务类Class对象
     * @param ccbAction     副表DO转BO Action
     * @param <VO>          需实现{@link PkFieldValue}接口
     * @param <R>           与主表VO中主键相同的数据类型
     * @param <C>           与副表中主键相同的数据类型
     * @param <V>           关系表中额外有价值字段
     * @param <RC>          关联表DO实体类类型 需要实现{@link Table.Cell}接口
     * @param <CC>          副表DO实体类类型
     * @param <CCB>         副表BO类型 需实现{@link IdValue}接口
     */
    public static <VO extends PkFieldValue<R, CCB>, R, C extends Serializable, V, RC extends Table.Cell<R, C, V>, CC, CCB extends IdValue<C, V>> void injectField(List<VO> voList, Class<? extends IService<RC>> rServiceClazz, SFunction<RC, R> rowAction, Class<? extends IService<CC>> vServiceClazz, Function<CC, CCB> ccbAction) {
        injectField(voList, getBean(rServiceClazz), rowAction, getBean(vServiceClazz), ccbAction);
    }

    /**
     * 多对多 单条记录场景 属性注入
     * <p>
     * 适用于关系表不仅做关联、还有实际属性的情形
     * <p>
     * 主表(Student)、关联表(StuSubRelation)、副表(Subject)
     * <p>
     * 关系表中存在实际意义的字段，比方说学生与课程关系表中存在分数这个属性字段
     *
     * @param data      主表VO实例
     * @param rService  关联表服务类实例
     * @param rowAction 关联表与主表逻辑外键关联列（方法引用表示）
     * @param vService  副表服务类实例
     * @param ccbAction 副表DO转BO Action
     * @param <VO>      需实现{@link PkFieldValue}接口
     * @param <R>       与主表VO中主键相同的数据类型
     * @param <C>       与副表中主键相同的数据类型
     * @param <V>       关系表中额外有价值字段
     * @param <RC>      关联表DO实体类类型 需要实现{@link Table.Cell}接口
     * @param <CC>      副表DO实体类类型
     * @param <CCB>     副表BO类型 需实现{@link IdValue}接口
     */
    public static <VO extends PkFieldValue<R, CCB>, R, C extends Serializable, V, RC extends Table.Cell<R, C, V>, CC, CCB extends IdValue<C, V>> void injectField(VO data, IService<RC> rService, SFunction<RC, R> rowAction, IService<CC> vService, Function<CC, CCB> ccbAction) {
        injectField(Collections.singletonList(data), rService, rowAction, vService, ccbAction);
    }

    /**
     * 多对多 单条记录 属性注入
     * <p>
     * 适用于关系表不仅做关联、还有实际属性的情形
     * <p>
     * 主表(Student)、关联表(StuSubRelation)、副表(Subject)
     * <p>
     * 关系表中存在实际意义的字段，比方说学生与课程关系表中存在分数这个属性字段
     *
     * @param data          主表VO实例
     * @param rServiceClazz 关联表服务类Class对象
     * @param rowAction     关联表与主表逻辑外键关联列（方法引用表示）
     * @param vServiceClazz 副表服务类Class对象
     * @param ccbAction     副表DO转BO Action
     * @param <VO>          需实现{@link PkFieldValue}接口
     * @param <R>           与主表VO中主键相同的数据类型
     * @param <C>           与副表中主键相同的数据类型
     * @param <V>           关系表中额外有价值字段
     * @param <RC>          关联表DO实体类类型 需要实现{@link Table.Cell}接口
     * @param <CC>          副表DO实体类类型
     * @param <CCB>         副表BO类型 需实现{@link IdValue}接口
     */
    public static <VO extends PkFieldValue<R, CCB>, R, C extends Serializable, V, RC extends Table.Cell<R, C, V>, CC, CCB extends IdValue<C, V>> void injectField(VO data, Class<? extends IService<RC>> rServiceClazz, SFunction<RC, R> rowAction, Class<? extends IService<CC>> vServiceClazz, Function<CC, CCB> ccbAction) {
        injectField(Collections.singletonList(data), getBean(rServiceClazz), rowAction, getBean(vServiceClazz), ccbAction);
    }


    /**
     * 多对多 分页场景 属性注入
     * <p>
     * 适用于关系表不仅做关联、还有实际属性的情形
     * <p>
     * 主表(Student)、关联表(StuSubRelation)、副表(Subject)
     * <p>
     * 关系表中存在实际意义的字段，比方说学生与课程关系表中存在分数这个属性字段
     *
     * @param voPage    主表VO分页实例
     * @param rService  关联表服务类实例
     * @param rowAction 关联表与主表逻辑外键关联列（方法引用表示）
     * @param vService  副表服务类实例
     * @param ccbAction 副表DO转BO Action
     * @param <VO>      需实现{@link PkFieldValue}接口
     * @param <R>       与主表VO中主键相同的数据类型
     * @param <C>       与副表中主键相同的数据类型
     * @param <V>       关系表中额外有价值字段
     * @param <RC>      关联表DO实体类类型 需要实现{@link Table.Cell}接口
     * @param <CC>      副表DO实体类类型
     * @param <CCB>     副表BO类型 需实现{@link IdValue}接口
     */
    public static <VO extends PkFieldValue<R, CCB>, R, C extends Serializable, V, RC extends Table.Cell<R, C, V>, CC, CCB extends IdValue<C, V>> void injectField(IPage<VO> voPage, IService<RC> rService, SFunction<RC, R> rowAction, IService<CC> vService, Function<CC, CCB> ccbAction) {
        List<VO> voList = voPage.getRecords();
        injectField(voList, rService, rowAction, vService, ccbAction);
    }


    /**
     * 多对多 分页场景 属性注入
     * <p>
     * 适用于关系表不仅做关联、还有实际属性的情形
     * <p>
     * 主表(Student)、关联表(StuSubRelation)、副表(Subject)
     * <p>
     * 关系表中存在实际意义的字段，比方说学生与课程关系表中存在分数这个属性字段
     *
     * @param voPage        主表VO分页实例
     * @param rServiceClazz 关联表服务类Class对象
     * @param rowAction     关联表与主表逻辑外键关联列（方法引用表示）
     * @param vServiceClazz 副表服务类Class对象
     * @param ccbAction     副表DO转BO Action
     * @param <VO>          需实现{@link PkFieldValue}接口
     * @param <R>           与主表VO中主键相同的数据类型
     * @param <C>           与副表中主键相同的数据类型
     * @param <V>           关系表中额外有价值字段
     * @param <RC>          关联表DO实体类类型 需要实现{@link Table.Cell}接口
     * @param <CC>          副表DO实体类类型
     * @param <CCB>         副表BO类型 需实现{@link IdValue}接口
     */
    public static <VO extends PkFieldValue<R, CCB>, R, C extends Serializable, V, RC extends Table.Cell<R, C, V>, CC, CCB extends IdValue<C, V>> void injectField(IPage<VO> voPage, Class<? extends IService<RC>> rServiceClazz, SFunction<RC, R> rowAction, Class<? extends IService<CC>> vServiceClazz, Function<CC, CCB> ccbAction) {
        injectField(voPage, getBean(rServiceClazz), rowAction, getBean(vServiceClazz), ccbAction);
    }


    // ⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇ 关联表中只有关系 不含额外属性 ⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇

    /**
     * 多对多 单条记录 属性注入
     * <p>
     * 适用于关系表仅做关联 没有实际属性的情况
     * <p>
     * 主表(User)、关联表(UserRoleRelation)、副表(Role)
     *
     * @param data      主表VO实例
     * @param rService  关联表服务类实例
     * @param keyAction 关联表与主表逻辑外键关联列（方法引用表示）
     * @param vService  副表服务类实例
     * @param <VO>      需实现{@link PkFieldValue}接口
     * @param <K>       与主表VO中主键相同的数据类型
     * @param <V>       与副表中主键相同的数据类型
     * @param <RC>      关联表DO实体类类型 需要实现{@link IEntry}接口
     * @param <VC>      副表DO实体类类型
     */
    public static <VO extends PkFieldValue<K, VC>, K, V extends Serializable, RC extends IEntry<K, V>, VC> void injectField(VO data, IService<RC> rService, SFunction<RC, K> keyAction, IService<VC> vService) {
        injectField(Collections.singletonList(data), rService, keyAction, vService);
    }

    /**
     * 多对多 列表查询场景 属性注入
     * <p>
     * 适用于关系表仅做关联 没有实际属性的情况
     * <p>
     * 主表(User)、关联表(UserRoleRelation)、副表(Role)
     *
     * @param data          主表VO实例
     * @param rServiceClazz 关联表服务类Class对象
     * @param keyAction     关联表与主表逻辑外键关联列（方法引用表示）
     * @param vServiceClazz 副表服务类Class对象
     * @param <VO>          需实现{@link PkFieldValue}接口
     * @param <K>           与主表VO中主键相同的数据类型
     * @param <V>           与副表中主键相同的数据类型
     * @param <RC>          关联表DO实体类类型 需要实现{@link IEntry}接口
     * @param <VC>          副表DO实体类类型
     */
    public static <VO extends PkFieldValue<K, VC>, K, V extends Serializable, RC extends IEntry<K, V>, VC> void injectField(VO data, Class<? extends IService<RC>> rServiceClazz, SFunction<RC, K> keyAction, Class<? extends IService<VC>> vServiceClazz) {
        injectField(data, getBean(rServiceClazz), keyAction, getBean(vServiceClazz));
    }


    /**
     * 多对多 列表查询场景 属性注入
     * <p>
     * 适用于关系表仅做关联 没有实际属性的情况
     * <p>
     * 主表(User)、关联表(UserRoleRelation)、副表(Role)
     *
     * @param voList        列表集合实例
     * @param rServiceClazz 关联表服务类Class对象
     * @param keyAction     关联表与主表逻辑外键关联列（方法引用表示）
     * @param vServiceClazz 副表服务类Class对象
     * @param <VO>          需实现{@link PkFieldValue}接口
     * @param <K>           与主表VO中主键相同的数据类型
     * @param <V>           与副表中主键相同的数据类型
     * @param <RC>          关联表DO实体类类型 需要实现{@link IEntry}接口
     * @param <VC>          副表DO实体类类型
     */
    public static <VO extends PkFieldValue<K, VC>, K, V extends Serializable, RC extends IEntry<K, V>, VC> void injectField(List<VO> voList, Class<? extends IService<RC>> rServiceClazz, SFunction<RC, K> keyAction, Class<? extends IService<VC>> vServiceClazz) {
        injectField(voList, getBean(rServiceClazz), keyAction, getBean(vServiceClazz));
    }


    /**
     * 多对多 列表查询场景 属性注入
     * <p>
     * 适用于关系表仅做关联 没有实际属性的情况
     * <p>
     * 主表(User)、关联表(UserRoleRelation)、副表(Role)
     *
     * @param voList    列表集合实例
     * @param rService  关联表服务类实例
     * @param keyAction 关联表与主表逻辑外键关联列（方法引用表示）
     * @param vService  副表服务类实例
     * @param <VO>      需实现{@link PkFieldValue}接口
     * @param <K>       与主表VO中主键相同的数据类型
     * @param <V>       与副表中主键相同的数据类型
     * @param <RC>      关联表DO实体类类型 需要实现{@link IEntry}接口
     * @param <VC>      副表DO实体类类型
     */
    public static <VO extends PkFieldValue<K, VC>, K, V extends Serializable, RC extends IEntry<K, V>, VC> void injectField(List<VO> voList, IService<RC> rService, SFunction<RC, K> keyAction, IService<VC> vService) {
        if (ColUtils.isNotEmpty(voList)) {
            // 取出主键学生主键ID的集合
            Set<K> stuIds = EntityUtils.toSet(voList, PkFieldValue::pkVal);
            // 通过学生ID 批量从关系表中查询数据
            LambdaQueryWrapper<RC> relationWrapper = Wrappers.lambdaQuery(rService.getEntityClass()).in(keyAction, stuIds);
            List<RC> relationList = rService.getBaseMapper().selectList(relationWrapper);
            // 取出课程ID集合（去重）
            Set<V> subIds = EntityUtils.toSet(relationList, IEntry::getValue);
            if (ColUtils.isNotEmpty(subIds)) {
                List<VC> subjectList = vService.getBaseMapper().selectBatchIds(subIds);

                Map<V, VC> map = EntityUtils.toMap(subjectList, (Function<VC, V>) e -> PlusUtils.pkVal(e, vService.getEntityClass()));

                Map<K, List<V>> rListMap = EntityUtils.groupBy(relationList, IEntry::getKey, IEntry::getValue);
                for (VO studentVo : voList) {
                    K k = EntityUtils.toObj(studentVo, PkFieldValue::pkVal);
                    studentVo.setList(MapUtils.getCollection(map, rListMap.get(k)));
                }
            }
        }
    }


    /**
     * 多对多分页场景属性注入
     * <p>
     * 适用于关系表仅做关联 没有实际属性的情况
     * <p>
     * 主表(User)、关联表(UserRoleRelation)、副表(Role)
     *
     * @param voPage    分页实例
     * @param rService  关联表服务类实例
     * @param keyAction 关联表与主表逻辑外键关联列（方法引用表示）
     * @param vService  副表服务类实例
     * @param <VO>      需实现{@link PkFieldValue}接口
     * @param <K>       与主表VO中主键相同的数据类型
     * @param <V>       与副表中主键相同的数据类型
     * @param <RC>      关联表DO实体类类型 需要实现{@link IEntry}接口
     * @param <VC>      副表DO实体类类型
     */
    public static <VO extends PkFieldValue<K, VC>, K, V extends Serializable, RC extends IEntry<K, V>, VC> void injectField(IPage<VO> voPage, IService<RC> rService, SFunction<RC, K> keyAction, IService<VC> vService) {
        List<VO> voList = voPage.getRecords();
        injectField(voList, rService, keyAction, vService);
    }

    /**
     * 多对多分页场景属性注入
     * <p>
     * 适用于关系表仅做关联 没有实际属性的情况
     * <p>
     * 主表(User)、关联表(UserRoleRelation)、副表(Role)
     *
     * @param voPage        分页实例
     * @param rServiceClazz 关联表服务类Class对象
     * @param keyAction     关联表与主表逻辑外键关联列（方法引用表示）
     * @param vServiceClazz 副表服务类Class对象
     * @param <VO>          需实现{@link PkFieldValue}接口
     * @param <K>           与主表VO中主键相同的数据类型
     * @param <V>           与副表中主键相同的数据类型
     * @param <RC>          关联表DO实体类类型 需要实现{@link IEntry}接口
     * @param <VC>          副表DO实体类类型
     */
    public static <VO extends PkFieldValue<K, VC>, K, V extends Serializable, RC extends IEntry<K, V>, VC> void injectField(IPage<VO> voPage, Class<? extends IService<RC>> rServiceClazz, SFunction<RC, K> keyAction, Class<? extends IService<VC>> vServiceClazz) {
        injectField(voPage, getBean(rServiceClazz), keyAction, getBean(vServiceClazz));
    }

}

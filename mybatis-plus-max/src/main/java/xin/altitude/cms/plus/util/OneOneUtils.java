/*
 *
 * Copyright (c) 2020-2023, 赛泰先生 (http://www.altitude.xin).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package xin.altitude.cms.plus.util;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xin.altitude.cms.common.util.BeanCopyUtils;
import xin.altitude.cms.common.util.EntityUtils;
import xin.altitude.cms.common.util.GuavaUtils;
import xin.altitude.cms.common.util.RefUtils;
import xin.altitude.cms.plus.lang.FkField;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static xin.altitude.cms.common.util.SpringUtils.getBean;

/**
 * 一对一查询属性注入工具类
 * <p>
 * 一对一查询典型场景是用户(User)与部门(Dept)，一个用户对应一个部门
 * <p>
 * 一对一查询属性注入{@link OneOneUtils} | 一对多查询属性注入{@link OneMoreUtils} | 多对多查询属性注入{@link MoreMoreUtils}
 *
 * @author <a href="http://www.altitude.xin" target="_blank">赛泰先生</a>
 * @author <a href="https://gitee.com/decsa/ucode-cms-vue" target="_blank">UCode CMS</a>
 * @author <a href="https://space.bilibili.com/1936685014" target="_blank">B站视频</a>
 * @since 1.6.4
 **/
public class OneOneUtils {
    private static final String DEBUG_LOG_FORMAT = "实体类%s中的属性[%s]已完成值注入，强烈建议使用继承保持源与目标属性名称一致";
    private final static Logger LOGGER = LoggerFactory.getLogger(OneOneUtils.class);
    private static final String WARN_LOG = "本次属性注入操作已跳过，请通过可变参数[injectColumns]至少指定一个待注入属性";

    private OneOneUtils() {
    }

    // ⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇ 最通用的实现、耦合度最低、代价是参数略多一点【副表显示指明主键列】 ⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇

    /**
     * <p>适用于一对一查询VO属性注入</p>
     * <p>主副表的定义：主表是指包含外键的表</p>
     *
     * @param <V>           主表对应的实体类VO
     * @param <S>           副表对应的实体类DO
     * @param <R>           主表外键、副表主键对应的数据类型
     * @param data          主表对应的VO实例
     * @param fkColumn      主表关联外键列（字段） 方法引用表示
     * @param serviceClazz  副表对应的IService实现类Class对象
     * @param pkColumn      副表对应的主键（字段）方法引用表示
     * @param injectColumns 需要注入的列（字段） 方法引用表示
     */
    @SafeVarargs
    public static <V, S, R extends Serializable> void injectField(V data, final SFunction<V, R> fkColumn, Class<? extends IService<S>> serviceClazz, final SFunction<S, R> pkColumn, SFunction<V, ? extends Serializable>... injectColumns) {
        injectField(data, fkColumn, getBean(serviceClazz), pkColumn, injectColumns);
    }

    /**
     * <p>适用于一对一查询VO属性注入</p>
     * <p>主副表的定义：主表是指包含外键的表</p>
     *
     * @param <V>           主表对应的实体类VO
     * @param <S>           副表对应的实体类DO
     * @param <R>           主表外键、副表主键对应的数据类型
     * @param data          主表对应的VO实例
     * @param fkColumn      主表关联外键列（字段） 方法引用表示
     * @param pkColumn      副表对应的主键（字段）方法引用表示
     * @param injectColumns 需要注入的列（字段） 方法引用表示
     */
    @SafeVarargs
    public static <V, S, R> void injectField(V data, final SFunction<V, R> fkColumn, IService<S> service, final SFunction<S, R> pkColumn, SFunction<V, ? extends Serializable>... injectColumns) {
        injectField(Collections.singletonList(data), fkColumn, service, pkColumn, injectColumns);
    }

    /**
     * <p>适用于一对一查询VO属性注入</p>
     * <p>
     * 通过反射的方式给关联表查询注入属性值
     * 需要说明的是 相较于手动编码 反射的执行效率略微差点
     * 本方法优点是能够提高开发效率
     * 后续考虑逆向工程优化性能
     * </p>
     * <p>主副表的定义：主表是指包含外键的表</p>
     *
     * @param <V>           主表对应的实体类VO
     * @param <S>           副表对应的实体类DO
     * @param <R>           主表外键、副表主键对应的数据类型
     * @param data          主表对应的VO集合实例
     * @param fkColumn      主表关联外键列（字段） 方法引用表示
     * @param serviceClazz  副表对应的IService实现类Class对象
     * @param pkColumn      副表对应的主键（字段）方法引用表示
     * @param injectColumns 需要注入的列（字段） 方法引用表示
     */
    @SafeVarargs
    public static <V, S, R> void injectField(List<V> data, final SFunction<V, R> fkColumn, Class<? extends IService<S>> serviceClazz, final SFunction<S, R> pkColumn, SFunction<V, ? extends Serializable>... injectColumns) {
        injectField(data, fkColumn, getBean(serviceClazz), pkColumn, injectColumns);
    }

    /**
     * <p>适用于一对一查询VO属性注入</p>
     * <p>主副表的定义：主表是指包含外键的表</p>
     *
     * @param <V>           主表对应的实体类VO
     * @param <S>           副表对应的实体类DO
     * @param <R>           主表外键、副表主键对应的数据类型
     * @param data          主表对应的VO集合实例
     * @param fkColumn      主表关联外键列（字段） 方法引用表示
     * @param pkColumn      副表对应的主键（字段）方法引用表示
     * @param injectColumns 需要注入的列（字段） 方法引用表示
     */
    @SafeVarargs
    public static <V, S, R> void injectField(List<V> data, final SFunction<V, R> fkColumn, IService<S> iService, final SFunction<S, R> pkColumn, SFunction<V, ? extends Serializable>... injectColumns) {
        // 获取主表关联外键的集合（去重）
        Set<? extends R> ids = EntityUtils.toSet(data, fkColumn);
        // 如果集合元素个数不为空 则进行后续操作
        if (ids.size() > 0 && injectColumns.length > 0) {
            // 主表关联外键对应的字段名字符串
            String fieldName = RefUtils.getFiledName(fkColumn);
            List<String> injectFiledNames = RefUtils.getFiledNames(injectColumns);
            String[] selectField = {fieldName, String.join(",", injectFiledNames)};
            // 数据库查询字段需要下划线表示
            String selectStr = Arrays.stream(selectField).map(GuavaUtils::toUnderScoreCase).collect(Collectors.joining(","));
            // 构造副表查询条件(查询指定列元素)
            QueryWrapper<S> wrapper = Wrappers.query(RefUtils.newInstance(iService.getEntityClass())).select(selectStr).in(GuavaUtils.toUnderScoreCase(fieldName), ids);
            // 通过主表的外键查询关联副表符合条件的数据
            List<S> list = iService.getBaseMapper().selectList(wrapper);
            // 将list转换为map 其中Key为副表主键 Value为副表类型实例本身
            Map<R, S> map = EntityUtils.toMap(list, pkColumn, e -> e);
            for (V v : data) {
                // 获取当前主表VO对象实例关联外键的值
                R r = RefUtils.getFieldValue(v, fieldName);
                // 从map中取出关联副表的对象实例
                S s = map.get(r);
                // 使用Spring内置的属性复制方法
                BeanCopyUtils.copyProperties(s, v, injectFiledNames);
            }
            LOGGER.debug(String.format(DEBUG_LOG_FORMAT, data.get(0).getClass().getSimpleName(), String.join(",", injectFiledNames)));
        } else if (injectColumns.length == 0) {
            LOGGER.warn(WARN_LOG);
        }
    }

    /**
     * @param page          主表对应的VO分页实例
     * @param fkColumn      主表关联外键列（字段） 方法引用表示
     * @param clazz         副表对应的IService实例
     * @param pkColumn      副表对应的主键（字段）方法引用表示
     * @param injectColumns 需要注入的列（字段） 方法引用表示
     * @param <V>           主表对应的实体类VO
     * @param <S>           副表对应的实体类DO
     * @param <R>           主表外键副表主键对应的数据类型
     */
    @SafeVarargs
    public static <V, S, R> void injectField(IPage<V> page, final SFunction<V, R> fkColumn, Class<? extends IService<S>> clazz, final SFunction<S, R> pkColumn, SFunction<V, ? extends Serializable>... injectColumns) {
        injectField(page.getRecords(), fkColumn, clazz, pkColumn, injectColumns);
    }

    /**
     * @param page          主表对应的VO分页实例
     * @param fkColumn      主表关联外键列（字段） 方法引用表示
     * @param service       副表对应的IService实例
     * @param pkColumn      副表对应的主键（字段）方法引用表示
     * @param injectColumns 需要注入的列（字段） 方法引用表示
     * @param <V>           主表对应的实体类VO
     * @param <S>           副表对应的实体类DO
     * @param <R>           主表外键副表主键对应的数据类型
     */
    @SafeVarargs
    public static <V, S, R> void injectField(IPage<V> page, final SFunction<V, R> fkColumn, IService<S> service, final SFunction<S, R> pkColumn, SFunction<V, ? extends Serializable>... injectColumns) {
        injectField(page.getRecords(), fkColumn, service, pkColumn, injectColumns);
    }


    // ⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇ 最通用的实现、耦合度最低、代价是参数略多一点【副表无需指明主键列】 ⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇


    /**
     * <p>适用于一对一查询VO属性注入</p>
     * <p>主副表的定义：主表是指包含外键的表</p>
     *
     * @param <V>           主表对应的实体类VO
     * @param <S>           副表对应的实体类DO
     * @param <R>           主表外键、副表主键对应的数据类型
     * @param data          主表对应的VO实例
     * @param fkColumn      主表关联外键列（字段） 方法引用表示
     * @param injectColumns 需要注入的列（字段） 方法引用表示
     */
    @SafeVarargs
    public static <V, S, R extends Serializable> void injectField(V data, final SFunction<V, R> fkColumn, IService<S> service, SFunction<V, ? extends Serializable>... injectColumns) {
        injectField(Collections.singletonList(data), fkColumn, service, injectColumns);
    }

    /**
     * <p>适用于一对一查询VO属性注入</p>
     * <p>主副表的定义：主表是指包含外键的表</p>
     *
     * @param <V>           主表对应的实体类VO
     * @param <S>           副表对应的实体类DO
     * @param <R>           主表外键、副表主键对应的数据类型
     * @param data          主表对应的VO实例
     * @param fkColumn      主表关联外键列（字段） 方法引用表示
     * @param injectColumns 需要注入的列（字段） 方法引用表示
     */
    @SafeVarargs
    public static <V, S, R extends Serializable> void injectField(V data, final SFunction<V, R> fkColumn, Class<? extends IService<S>> serviceClazz, SFunction<V, ? extends Serializable>... injectColumns) {
        injectField(data, fkColumn, getBean(serviceClazz), injectColumns);
    }


    /**
     * <p>适用于一对一查询VO属性注入</p>
     * <p>主副表的定义：主表是指包含外键的表</p>
     *
     * @param <V>           主表对应的实体类VO
     * @param <S>           副表对应的实体类DO
     * @param <R>           主表外键、副表主键对应的数据类型
     * @param data          主表对应的VO集合实例
     * @param fkColumn      主表关联外键列（字段） 方法引用表示
     * @param injectColumns 需要注入的列（字段） 方法引用表示
     */
    @SafeVarargs
    public static <V, S, R extends Serializable> void injectField(List<V> data, final SFunction<V, R> fkColumn, IService<S> service, SFunction<V, ? extends Serializable>... injectColumns) {
        // 获取主表关联外键的集合（去重）
        Set<R> ids = EntityUtils.toSet(data, fkColumn);
        // 如果集合元素个数不为空 则进行后续操作
        if (ids.size() > 0 && injectColumns.length > 0) {
            // 主表关联外键对应的字段名字符串
            String fieldName = RefUtils.getFiledName(fkColumn);
            List<String> injectFiledNames = RefUtils.getFiledNames(injectColumns);
            String[] selectField = injectFiledNames.toArray(new String[0]);
            // 通过主表的外键查询关联副表符合条件的数据
            List<S> sList = service.getBaseMapper().selectBatchIds(ids);
            // 将list转换为map 其中Key为副表主键 Value为副表类型实例本身
            Map<R, S> map = EntityUtils.toMap(sList, e -> PlusUtils.pkVal(e, service.getEntityClass()), e -> e);
            for (V v : data) {
                // 获取当前主表VO对象实例关联外键的值
                R r = RefUtils.getFieldValue(v, fieldName);
                // 从map中取出关联副表的对象实例
                S s = map.get(r);
                // 使用Spring内置的属性复制方法
                BeanCopyUtils.copyProperties(s, v, injectFiledNames);
            }
            LOGGER.debug(String.format(DEBUG_LOG_FORMAT, data.get(0).getClass().getSimpleName(), String.join(",", injectFiledNames)));
        } else if (injectColumns.length == 0) {
            LOGGER.warn(WARN_LOG);
        }
    }


    /**
     * <p>适用于一对一查询VO属性注入</p>
     * <p>主副表的定义：主表是指包含外键的表</p>
     *
     * @param <V>           主表对应的实体类VO
     * @param <S>           副表对应的实体类DO
     * @param <R>           主表外键、副表主键对应的数据类型
     * @param data          主表对应的VO集合实例
     * @param fkColumn      主表关联外键列（字段） 方法引用表示
     * @param injectColumns 需要注入的列（字段） 方法引用表示
     */
    @SafeVarargs
    public static <V, S, R extends Serializable> void injectField(List<V> data, final SFunction<V, R> fkColumn, Class<? extends IService<S>> serviceClazz, SFunction<V, ? extends Serializable>... injectColumns) {
        injectField(data, fkColumn, getBean(serviceClazz), injectColumns);
    }


    /**
     * 通过反射的方式给关联表查询注入属性值
     * 需要说明的是 相较于手动编码 反射的执行效率略差
     * 本方法能够提高开发效率 后续考虑逆向工程优化性能
     *
     * @param page          主表对应的VO分页实例
     * @param fkColumn      主表关联外键列（字段） 方法引用表示
     * @param serviceClazz  副表对应的IService实例
     * @param injectColumns 需要注入的列（字段） 方法引用表示
     * @param <V>           主表对应的实体类VO
     * @param <S>           副表对应的实体类DO
     * @param <R>           主表外键副表主键对应的数据类型
     */
    @SafeVarargs
    public static <V, S, R extends Serializable> void injectField(IPage<V> page, final SFunction<V, R> fkColumn, Class<? extends IService<S>> serviceClazz, SFunction<V, ? extends Serializable>... injectColumns) {
        injectField(page.getRecords(), fkColumn, serviceClazz, injectColumns);
    }


    /**
     * @param page          主表对应的VO分页实例
     * @param fkColumn      主表关联外键列（字段） 方法引用表示
     * @param service       副表对应的IService实例
     * @param injectColumns 需要注入的列（字段） 方法引用表示
     * @param <V>           主表对应的实体类VO
     * @param <S>           副表对应的实体类DO
     * @param <R>           主表外键副表主键对应的数据类型
     */
    @SafeVarargs
    public static <V, S, R extends Serializable> void injectField(IPage<V> page, final SFunction<V, R> fkColumn, IService<S> service, SFunction<V, ? extends Serializable>... injectColumns) {
        injectField(page.getRecords(), fkColumn, service, injectColumns);
    }

    // ⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇ 主表VO需要实现PkField接口 ⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇

    /**
     * <p>适用于一对一 单条记录查询 属性注入</p>
     * <p>主副表的定义：主表是指包含外键的表</p>
     *
     * @param <V>           主表对应的实体类VO  需要实现{@link FkField}
     * @param <S>           副表对应的实体类DO
     * @param data          主表对应的VO实例
     * @param serviceClazz  副表对应的IService实现类Class对象
     * @param injectColumns 需要注入的列（字段） 方法引用表示
     */
    @SafeVarargs
    public static <V extends FkField<R>, S, R extends Serializable> void injectField(V data, Class<? extends IService<S>> serviceClazz, SFunction<V, ? extends Serializable>... injectColumns) {
        injectField(data, getBean(serviceClazz), injectColumns);
    }

    /**
     * <p>适用于一对一查询VO属性注入</p>
     * <p>主副表的定义：主表是指包含外键的表</p>
     *
     * @param <V>           主表对应的实体类VO  需要实现{@link FkField}
     * @param <S>           副表对应的实体类DO
     * @param <R>           主表外键、副表主键对应的数据类型
     * @param data          主表对应的VO实例
     * @param injectColumns 需要注入的列（字段） 方法引用表示
     */
    @SafeVarargs
    public static <V extends FkField<R>, S, R extends Serializable> void injectField(V data, IService<S> service, SFunction<V, ? extends Serializable>... injectColumns) {
        injectField(Collections.singletonList(data), service, injectColumns);
    }


    /**
     * <p>适用于一对一查询VO属性注入</p>
     *
     * <p>主副表的定义：主表是指包含外键的表</p>
     *
     * @param <V>           主表对应的实体类VO
     * @param <S>           副表对应的实体类DO
     * @param <R>           主表外键、副表主键对应的数据类型
     * @param data          主表对应的VO集合实例
     * @param serviceClazz  副表对应的IService实现类Class对象
     * @param injectColumns 需要注入的列（字段） 方法引用表示
     */
    @SafeVarargs
    public static <V extends FkField<R>, S, R extends Serializable> void injectField(List<V> data, Class<? extends IService<S>> serviceClazz, SFunction<V, ? extends Serializable>... injectColumns) {
        injectField(data, getBean(serviceClazz), injectColumns);
    }

    /**
     * <p>适用于一对一查询VO属性注入</p>
     * <p>主副表的定义：主表是指包含外键的表</p>
     *
     * @param <V>           主表对应的实体类VO
     * @param <S>           副表对应的实体类DO
     * @param <R>           主表外键、副表主键对应的数据类型
     * @param data          主表对应的VO集合实例
     * @param injectColumns 需要注入的列（字段） 方法引用表示
     */
    @SafeVarargs
    public static <V extends FkField<R>, S, R extends Serializable> void injectField(List<V> data, IService<S> service, SFunction<V, ? extends Serializable>... injectColumns) {
        // 获取主表关联外键的集合（去重）
        Set<R> ids = EntityUtils.toSet(data, FkField::fkVal);
        // 如果集合元素个数不为空 则进行后续操作
        if (ids.size() > 0 && injectColumns.length > 0) {
            List<String> injectFiledNames = RefUtils.getFiledNames(injectColumns);
            // 通过主表的外键查询关联副表符合条件的数据
            List<S> ss = service.getBaseMapper().selectBatchIds(ids);
            // 将集合转化成map
            Map<R, S> map = EntityUtils.toMap(ss, e -> PlusUtils.pkVal(e, service.getEntityClass()), Function.identity());
            for (V v : data) {
                // 从map中取出关联副表的对象实例
                Optional.ofNullable(v.fkVal()).ifPresent(e -> BeanCopyUtils.copyProperties(map.get(e), v, injectFiledNames));
            }
            LOGGER.debug(String.format(DEBUG_LOG_FORMAT, data.get(0).getClass().getSimpleName(), String.join(",", injectFiledNames)));
        } else if (injectColumns.length == 0) {
            LOGGER.warn(WARN_LOG);
        }
    }


    /**
     * @param page          主表对应的VO分页实例
     * @param serviceClazz  副表对应的IService实例
     * @param injectColumns 需要注入的列（字段） 方法引用表示
     * @param <V>           主表对应的实体类VO
     * @param <S>           副表对应的实体类DO
     * @param <R>           主表外键副表主键对应的数据类型
     */
    @SafeVarargs
    public static <V extends FkField<R>, S, R extends Serializable> void injectField(IPage<V> page, Class<? extends IService<S>> serviceClazz, SFunction<V, ? extends Serializable>... injectColumns) {
        injectField(page.getRecords(), serviceClazz, injectColumns);
    }

    /**
     * @param page          主表对应的VO分页实例
     * @param service       副表对应的IService实例
     * @param injectColumns 需要注入的列（字段） 方法引用表示
     * @param <V>           主表对应的实体类VO
     * @param <S>           副表对应的实体类DO
     * @param <R>           主表外键副表主键对应的数据类型
     */
    @SafeVarargs
    public static <V extends FkField<R>, S, R extends Serializable> void injectField(IPage<V> page, IService<S> service, SFunction<V, ? extends Serializable>... injectColumns) {
        injectField(page.getRecords(), service, injectColumns);
    }
}

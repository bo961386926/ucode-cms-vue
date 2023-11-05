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

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.core.toolkit.support.SFunction;
import com.baomidou.mybatisplus.extension.service.IService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xin.altitude.cms.common.util.EntityUtils;
import xin.altitude.cms.common.util.RefUtils;
import xin.altitude.cms.plus.lang.PkField;
import xin.altitude.cms.plus.lang.PkFieldValue;

import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import static xin.altitude.cms.common.util.SpringUtils.getBean;

/**
 * 一对多查询属性注入工具类
 * <p>
 * 一对多查询典型场景是部门(Dept)与用户(User)，一个部门对应多个用户
 * <p>
 * 主副表定义：主表(部门表)、副表(用户表)，其中用户表包含部门表的逻辑外键
 * <p>
 * 一对一查询属性注入{@link OneOneUtils} | 一对多查询属性注入{@link OneMoreUtils} | 多对多查询属性注入{@link MoreMoreUtils}
 *
 * @author <a href="http://www.altitude.xin" target="_blank">赛泰先生</a>
 * @author <a href="https://gitee.com/decsa/ucode-cms-vue" target="_blank">UCode CMS</a>
 * @author <a href="https://space.bilibili.com/1936685014" target="_blank">B站视频</a>
 * @since 1.6.4
 **/
public class OneMoreUtils {
    private static final String LOG_FORMAT = "实体类%s中的属性[%s]已完成值注入";
    private final static Logger LOGGER = LoggerFactory.getLogger(OneMoreUtils.class);

    private OneMoreUtils() {
    }


    // ⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇ 最通用的实现、耦合度最低、代价是参数略多一点 ⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇

    /**
     * 此方法适用于一对多查询
     * <p>
     * 查询主表VO单条数据
     * <p>
     * 使用前先使用{@link EntityUtils#toObj(Object, Function)}方法完成DO转VO
     * <p>
     * 主表VO需要无需额外实现任何其它接口
     * <p>
     * 相关主键列和待注入字段通过参数的形式（方法引用）注入
     * <p>
     * 适合VO中注入一个或者多个字段
     *
     * @param data         主表实体类VO的集合实例
     * @param pkColumn     副表实体类主键列（方法引用表示）
     * @param clazz        副表对应的{@link IService}实现类Class对象
     * @param fkColumn     副表实体类外键列（方法引用表示）
     * @param injectColumn 需要注入的列（字段） 方法引用表示
     * @param <VO>         主表实体类VO泛型
     * @param <R>          主表主键、副表外键对应的数据类型
     * @param <S>          副表对应的实体类DO
     */
    public static <VO, R, S> void injectField(VO data, SFunction<VO, R> pkColumn, Class<? extends IService<S>> clazz, SFunction<S, R> fkColumn, SFunction<VO, ?> injectColumn) {
        injectField(Collections.singletonList(data), pkColumn, getBean(clazz), fkColumn, injectColumn);
    }

    /**
     * 此方法适用于一对多查询
     * <p>
     * 查询主表VO单条数据
     * <p>
     * 使用前先使用{@link EntityUtils#toObj(Object, Function)}方法完成DO转VO
     * <p>
     * 主表VO需要无需额外实现任何其它接口
     * <p>
     * 相关主键列和待注入字段通过参数的形式（方法引用）注入
     * <p>
     * 适合VO中注入一个或者多个字段
     *
     * @param data         主表实体类VO的集合实例
     * @param pkColumn     副表实体类主键列（方法引用表示）
     * @param service      副表对应的{@link IService}实现类实例
     * @param fkColumn     副表实体类外键列（方法引用表示）
     * @param injectColumn 需要注入的列（字段） 方法引用表示
     * @param <VO>         主表实体类VO泛型
     * @param <R>          主表主键、副表外键对应的数据类型
     * @param <S>          副表对应的实体类DO
     */
    public static <VO, R, S> void injectField(VO data, SFunction<VO, R> pkColumn, IService<S> service, SFunction<S, R> fkColumn, SFunction<VO, ?> injectColumn) {
        injectField(Collections.singletonList(data), pkColumn, service, fkColumn, injectColumn);
    }


    /**
     * 此方法适用于一对多查询
     * <p>
     * 查询主表VO集合数据
     * <p>
     * 使用前先使用{@link EntityUtils#toList(Collection, Function)}方法完成集合DO转集合VO
     * <p>
     * 主表VO需要无需额外实现任何其它接口
     * <p>
     * 相关主键列和待注入字段通过参数的形式（方法引用）注入
     * <p>
     * 适合VO中注入一个或者多个字段
     *
     * @param data         主表实体类VO的集合实例
     * @param pkColumn     副表实体类主键列（方法引用表示）
     * @param service      副表对应的{@link IService}实现类实例
     * @param fkColumn     副表实体类外键列（方法引用表示）
     * @param injectColumn 需要注入的列（字段） 方法引用表示
     * @param <VO>         主表实体类VO泛型
     * @param <R>          主表主键、副表外键对应的数据类型
     * @param <S>          副表对应的实体类DO
     */
    public static <VO, R, S> void injectField(List<VO> data, SFunction<VO, R> pkColumn, IService<S> service, SFunction<S, R> fkColumn, SFunction<VO, ?> injectColumn) {
        Set<R> ids = EntityUtils.toSet(data, pkColumn);
        if (ids.size() > 0) {
            LambdaQueryWrapper<S> wrapper = Wrappers.lambdaQuery(service.getEntityClass()).in(fkColumn, ids);
            List<S> list = service.getBaseMapper().selectList(wrapper);
            // 以部门ID为单位对用户数据分组
            Map<R, List<S>> map = list.stream().collect(Collectors.groupingBy(fkColumn));
            String injectFieldName = RefUtils.getFiledName(injectColumn);
            String filedName = RefUtils.getFiledName(pkColumn);
            for (VO v : data) {
                // 获取当前主表VO对象实例关联外键的值
                R r = RefUtils.getFieldValue(v, filedName);
                // 从map中取出关联副表的对象实例
                List<S> s = map.get(r);
                // 使用反射给属性赋值
                RefUtils.setFiledValue(v, injectFieldName, s);
            }
            LOGGER.debug(String.format(LOG_FORMAT, data.get(0).getClass().getSimpleName(), injectFieldName));
        }
    }


    /**
     * 此方法适用于一对多查询
     * <p>
     * 查询主表VO集合数据
     * <p>
     * 使用前先使用{@link EntityUtils#toList(Collection, Function)}方法完成集合DO转集合VO
     * <p>
     * 主表VO需要无需额外实现任何其它接口
     * <p>
     * 相关主键列和待注入字段通过参数的形式（方法引用）注入
     * <p>
     * 适合VO中注入一个或者多个字段
     *
     * @param data         主表实体类VO的集合实例
     * @param pkColumn     副表实体类主键列（方法引用表示）
     * @param serviceClazz 副表对应的{@link IService}实现类Class对象
     * @param fkColumn     副表实体类外键列（方法引用表示）
     * @param injectColumn 需要注入的列（字段） 方法引用表示
     * @param <VO>         主表实体类VO泛型
     * @param <R>          主表主键、副表外键对应的数据类型
     * @param <S>          副表对应的实体类DO
     */
    public static <VO, R, S> void injectField(List<VO> data, SFunction<VO, R> pkColumn, Class<? extends IService<S>> serviceClazz, SFunction<S, R> fkColumn, SFunction<VO, ?> injectColumn) {
        injectField(data, pkColumn, getBean(serviceClazz), fkColumn, injectColumn);
    }

    /**
     * 此方法适用于一对多查询
     * <p>
     * 查询主表VO分页数据
     * <p>
     * 使用前先使用{@link EntityUtils#toList(Collection, Function)}方法完成分页DO转分页VO
     * <p>
     * 主表VO需要无需额外实现任何其它接口
     * <p>
     * 相关主键列和待注入字段通过参数的形式（方法引用）注入
     * <p>
     * 适合VO中注入一个或者多个字段
     *
     * @param page         副表实体类VO的分页实例
     * @param pkColumn     副表实体类主键列（方法引用表示）
     * @param serviceClazz 副表对应的{@link IService}实现类Class对象
     * @param fkColumn     副表实体类外键列（方法引用表示）
     * @param injectColumn 需要注入的列（字段） 方法引用表示
     * @param <VO>         主表实体类VO泛型
     * @param <R>          主表主键、副表外键对应的数据类型p
     * @param <S>          副表对应的实体类DO
     */
    public static <VO, R, S> void injectField(IPage<VO> page, SFunction<VO, R> pkColumn, Class<? extends IService<S>> serviceClazz, SFunction<S, R> fkColumn, SFunction<VO, ?> injectColumn) {
        injectField(page.getRecords(), pkColumn, getBean(serviceClazz), fkColumn, injectColumn);
    }

    /**
     * 此方法适用于一对多查询
     * <p>
     * 查询主表VO分页数据
     * <p>
     * 使用前先使用{@link EntityUtils#toList(Collection, Function)}方法完成分页DO转分页VO
     * <p>
     * 主表VO需要无需额外实现任何其它接口
     * <p>
     * 相关主键列和待注入字段通过参数的形式（方法引用）注入
     * <p>
     * 适合VO中注入一个或者多个字段
     *
     * @param page         副表实体类VO的分页实例
     * @param pkColumn     副表实体类主键列（方法引用表示）
     * @param service      副表对应的{@link IService}实现类实例
     * @param fkColumn     副表实体类外键列（方法引用表示）
     * @param injectColumn 需要注入的列（字段） 方法引用表示
     * @param <VO>         主表实体类VO泛型
     * @param <R>          主表主键、副表外键对应的数据类型p
     * @param <S>          副表对应的实体类DO
     */
    public static <VO, R, S> void injectField(IPage<VO> page, SFunction<VO, R> pkColumn, IService<S> service, SFunction<S, R> fkColumn, SFunction<VO, ?> injectColumn) {
        injectField(page.getRecords(), pkColumn, service, fkColumn, injectColumn);
    }


    // ⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇ 主表VO需要实现PkField接口 ⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇


    /**
     * 此方法适用于一对多查询
     * <p>
     * 查询主表VO单条数据
     * <p>
     * 使用前先使用{@link EntityUtils#toObj(Object, Function)}方法完成DO转VO
     * <p>
     * 主表VO需要实现{@link PkField}接口，接口中指定了主键的值
     * <p>
     * 待注入字段通过参数的方式提供（方法引用表示）
     * <p>
     * 此中方式API参数简洁 适合VO中注入一个或者多个字段
     *
     * @param data         主表实体类VO的集合实例
     * @param serviceClazz 副表对应的{@link IService}实现类Class对象
     * @param fkColumn     副表实体类外键列（方法引用表示）
     * @param injectColumn 需要注入的列（字段） 方法引用表示
     * @param <VO>         主表实体类VO泛型 需实现{@link PkField}接口
     * @param <R>          主表主键、副表外键对应的数据类型
     * @param <S>          副表对应的实体类DO
     */
    public static <VO extends PkField<R>, R extends Serializable, S> void injectField(VO data, Class<? extends IService<S>> serviceClazz, SFunction<S, R> fkColumn, SFunction<VO, ?> injectColumn) {
        injectField(Collections.singletonList(data), serviceClazz, fkColumn, injectColumn);
    }

    /**
     * 此方法适用于一对多查询
     * <p>
     * 查询主表VO单条数据
     * <p>
     * 使用前先使用{@link EntityUtils#toObj(Object, Function)}方法完成DO转VO
     * <p>
     * 主表VO需要实现{@link PkField}接口，接口中指定了主键的值
     * <p>
     * 待注入字段通过参数的方式提供（方法引用表示）
     * <p>
     * 此中方式API参数简洁 适合VO中注入一个或者多个字段
     *
     * @param data         主表实体类VO的集合实例
     * @param service      副表对应的{@link IService}实现类实例
     * @param fkColumn     副表实体类外键列（方法引用表示）
     * @param injectColumn 需要注入的列（字段） 方法引用表示
     * @param <VO>         主表实体类VO泛型 需实现{@link PkField}接口
     * @param <R>          主表主键、副表外键对应的数据类型
     * @param <S>          副表对应的实体类DO
     */
    public static <VO extends PkField<R>, R extends Serializable, S> void injectField(VO data, IService<S> service, SFunction<S, R> fkColumn, SFunction<VO, ?> injectColumn) {
        injectField(Collections.singletonList(data), service, fkColumn, injectColumn);
    }

    /**
     * 此方法适用于一对多查询
     * <p>
     * 查询主表VO分页数据
     * <p>
     * 使用前先使用{@link EntityUtils#toPage(IPage, Function)}方法完成分页DO转分页VO
     * <p>
     * 主表VO需要实现{@link PkField}接口，接口中指定了主键的值
     * <p>
     * 待注入字段通过参数的方式提供（方法引用表示）
     * <p>
     * 此中方式API参数简洁 适合VO中注入一个或者多个字段
     *
     * @param page         副表实体类VO的分页实例
     * @param serviceClazz 副表对应的{@link IService}实现类Class对象
     * @param fkColumn     副表实体类外键列（方法引用表示）
     * @param injectColumn 需要注入的列（字段） 方法引用表示
     * @param <VO>         主表实体类VO泛型 需实现{@link PkField}接口
     * @param <R>          主表主键、副表外键对应的数据类型
     * @param <S>          副表对应的实体类DO
     */
    public static <VO extends PkField<R>, R extends Serializable, S> void injectField(IPage<VO> page, Class<? extends IService<S>> serviceClazz, SFunction<S, R> fkColumn, SFunction<VO, ?> injectColumn) {
        injectField(page.getRecords(), serviceClazz, fkColumn, injectColumn);
    }


    /**
     * 此方法适用于一对多查询
     * <p>
     * 查询主表VO分页数据
     * <p>
     * 使用前先使用{@link EntityUtils#toPage(IPage, Function)}方法完成分页DO转分页VO
     * <p>
     * 主表VO需要实现{@link PkField}接口，接口中指定了主键的值
     * <p>
     * 待注入字段通过参数的方式提供（方法引用表示）
     * <p>
     * 此中方式API参数简洁 适合VO中注入一个或者多个字段
     *
     * @param page         副表实体类VO的分页实例
     * @param service      副表对应的{@link IService}实现类实例
     * @param fkColumn     副表实体类外键列（方法引用表示）
     * @param injectColumn 需要注入的列（字段） 方法引用表示
     * @param <VO>         主表实体类VO泛型 需实现{@link PkField}接口
     * @param <R>          主表主键、副表外键对应的数据类型p
     * @param <S>          副表对应的实体类DO
     */
    public static <VO extends PkField<R>, R extends Serializable, S> void injectField(IPage<VO> page, IService<S> service, SFunction<S, R> fkColumn, SFunction<VO, ?> injectColumn) {
        injectField(page.getRecords(), service, fkColumn, injectColumn);
    }

    /**
     * 此方法适用于一对多查询
     * <p>
     * 查询主表VO集合数据
     * <p>
     * 使用前先使用{@link EntityUtils#toList(Collection, Function)}方法完成列表DO转列表VO
     * <p>
     * 主表VO需要实现{@link PkField}接口，接口中指定了主键的值
     * <p>
     * 待注入字段通过参数的方式提供（方法引用表示）
     * <p>
     * 此中方式API参数简洁 适合VO中注入一个或者多个字段
     *
     * @param data         主表实体类VO的集合实例
     * @param serviceClazz 副表对应的{@link IService}实现类Class对象
     * @param fkColumn     副表实体类外键列（方法引用表示）
     * @param injectColumn 需要注入的列（字段） 方法引用表示
     * @param <VO>         主表实体类VO泛型 需实现{@link PkField}接口
     * @param <R>          主表主键、副表外键对应的数据类型
     * @param <S>          副表对应的实体类DO
     */
    public static <VO extends PkField<R>, R extends Serializable, S> void injectField(List<VO> data, Class<? extends IService<S>> serviceClazz, SFunction<S, R> fkColumn, SFunction<VO, ?> injectColumn) {
        injectField(data, getBean(serviceClazz), fkColumn, injectColumn);
    }

    /**
     * 此方法适用于一对多查询
     * <p>
     * 查询主表VO集合数据
     * <p>
     * 使用前先使用{@link EntityUtils#toList(Collection, Function)}方法完成列表DO转列表VO
     * <p>
     * 主表VO需要实现{@link PkField}接口，接口中指定了主键的值
     * <p>
     * 待注入字段通过参数的方式提供（方法引用表示）
     * <p>
     * 此中方式API参数简洁 适合VO中注入一个或者多个字段
     *
     * @param data         主表实体类VO的集合实例
     * @param service      副表对应的{@link IService}实现类实例
     * @param fkColumn     副表实体类外键列（方法引用表示）
     * @param injectColumn 需要注入的列（字段） 方法引用表示
     * @param <VO>         主表实体类VO泛型 需实现{@link PkField}接口
     * @param <R>          主表主键、副表外键对应的数据类型
     * @param <S>          副表对应的实体类DO
     */
    public static <VO extends PkField<R>, R extends Serializable, S> void injectField(List<VO> data, IService<S> service, SFunction<S, R> fkColumn, SFunction<VO, ?> injectColumn) {
        Set<R> ids = EntityUtils.toSet(data, PkField::pkVal);
        if (ids.size() > 0) {
            LambdaQueryWrapper<S> wrapper = Wrappers.lambdaQuery(service.getEntityClass()).in(fkColumn, ids);
            List<S> list = service.getBaseMapper().selectList(wrapper);
            // 以部门ID为单位对用户数据分组
            Map<R, List<S>> map = list.stream().collect(Collectors.groupingBy(fkColumn));
            String injectFieldName = RefUtils.getFiledName(injectColumn);
            for (VO v : data) {
                // 从map中取出关联副表的对象实例
                List<S> s = map.get(v.pkVal());
                // 使用反射给属性赋值
                RefUtils.setFiledValue(v, injectFieldName, s);
            }
            LOGGER.debug(String.format(LOG_FORMAT, data.get(0).getClass().getSimpleName(), injectFieldName));
        }
    }


    // ⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇ 主表VO需要实现PkFieldValue接口 ⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇⬇

    /**
     * 此方法适用于一对多查询
     * <p>
     * 查询主表VO单条数据
     * <p>
     * 使用前先使用{@link EntityUtils#toObj(Object, Function)}方法完成DO转VO
     * <p>
     * 主表VO需要实现{@link PkFieldValue}接口，接口中指定了主键的值和待注入字段的值
     * <p>
     * 此中方式API参数简洁 适合VO中注入一个字段，如果注入多个字段，请使用其它重返方法
     *
     * @param data     主表实体类VO的集合实例
     * @param service  副表对应的{@link IService}实现类实例
     * @param fkColumn 副表实体类外键列（方法引用表示）
     * @param <VO>     主表实体类VO泛型 需要实现{@link PkFieldValue}接口
     * @param <R>      主表主键、副表外键对应的数据类型
     * @param <S>      副表对应的实体类DO
     */
    public static <VO extends PkFieldValue<R, S>, R extends Serializable, S> void injectField(VO data, IService<S> service, SFunction<S, R> fkColumn) {
        injectField(Collections.singletonList(data), service, fkColumn);
    }


    /**
     * 此方法适用于一对多查询
     * <p>
     * 查询主表VO单条数据
     * <p>
     * 使用前先使用{@link EntityUtils#toObj(Object, Function)}方法完成DO转VO
     * <p>
     * 主表VO需要实现{@link PkFieldValue}接口，接口中指定了主键的值和待注入字段的值
     * <p>
     * 此中方式API参数简洁 适合VO中注入一个字段，如果注入多个字段，请使用其它重返方法
     *
     * @param data         主表实体类VO的集合实例
     * @param serviceClazz 副表对应的{@link IService}实现类Class对象
     * @param fkColumn     副表实体类外键列（方法引用表示）
     * @param <VO>         主表实体类VO泛型 需要实现{@link PkFieldValue}接口
     * @param <R>          主表主键、副表外键对应的数据类型
     * @param <S>          副表对应的实体类DO
     */
    public static <VO extends PkFieldValue<R, S>, R extends Serializable, S> void injectField(VO data, Class<? extends IService<S>> serviceClazz, SFunction<S, R> fkColumn) {
        injectField(Collections.singletonList(data), getBean(serviceClazz), fkColumn);
    }

    /**
     * 此方法适用于一对多查询
     * <p>
     * 查询主表VO集合数据
     * <p>
     * 使用前先使用{@link EntityUtils#toList(Collection, Function)}方法完成列表DO转列表VO
     * <p>
     * 主表VO需要实现{@link PkFieldValue}接口，接口中指定了主键的值和待注入字段的值
     * <p>
     * 此中方式API参数简洁 适合VO中注入一个字段，如果注入多个字段，请使用其它重返方法
     *
     * @param data         主表实体类VO的集合实例
     * @param serviceClazz 副表对应的{@link IService}实现类Class对象
     * @param fkColumn     副表实体类外键列（方法引用表示）
     * @param <VO>         主表实体类VO泛型 需要实现{@link PkFieldValue}接口
     * @param <R>          主表主键、副表外键对应的数据类型
     * @param <S>          副表对应的实体类DO
     */
    public static <VO extends PkFieldValue<R, S>, R extends Serializable, S> void injectField(List<VO> data, Class<? extends IService<S>> serviceClazz, SFunction<S, R> fkColumn) {
        injectField(data, getBean(serviceClazz), fkColumn);
    }

    /**
     * 此方法适用于一对多查询
     * <p>
     * 查询主表VO集合数据
     * <p>
     * 使用前先使用{@link EntityUtils#toList(Collection, Function)}方法完成列表DO转列表VO
     * <p>
     * 主表VO需要实现{@link PkFieldValue}接口，接口中指定了主键的值和待注入字段的值
     * <p>
     * 此中方式API参数简洁 适合VO中注入一个字段，如果注入多个字段，请使用其它重返方法
     *
     * @param data     主表实体类VO的集合实例
     * @param service  副表对应的{@link IService}实现类实例
     * @param fkColumn 副表实体类外键列（方法引用表示）
     * @param <VO>     主表实体类VO泛型 需要实现{@link PkFieldValue}接口
     * @param <R>      主表主键、副表外键对应的数据类型
     * @param <S>      副表对应的实体类DO
     */
    public static <VO extends PkFieldValue<R, S>, R extends Serializable, S> void injectField(List<VO> data, IService<S> service, SFunction<S, R> fkColumn) {
        Set<R> ids = EntityUtils.toSet(data, PkFieldValue::pkVal);
        if (ids.size() > 0) {
            LambdaQueryWrapper<S> wrapper = Wrappers.lambdaQuery(service.getEntityClass()).in(fkColumn, ids);
            List<S> list = service.getBaseMapper().selectList(wrapper);
            // 以部门ID为单位对用户数据分组
            Map<R, List<S>> map = list.stream().collect(Collectors.groupingBy(fkColumn));
            // 主表VO属性赋值
            data.forEach(e -> e.setList(map.get(e.pkVal())));
        }
    }

    /**
     * 此方法适用于一对多查询
     * <p>
     * 查询主表VO分页数据
     * <p>
     * 使用前先使用{@link EntityUtils#toPage(IPage, Function)}方法完成分页DO转分页VO
     * <p>
     * 主表VO需要实现{@link PkFieldValue}接口，接口中指定了主键的值和待注入字段的值
     * <p>
     * 此中方式API参数简洁 适合VO中注入一个字段，如果注入多个字段，请使用其它重返方法
     *
     * @param voPage   主表实体类VO的集合实例
     * @param service  副表对应的{@link IService}实现类实例
     * @param fkColumn 副表实体类外键列（方法引用表示）
     * @param <VO>     主表实体类VO泛型 需要实现{@link PkFieldValue}接口
     * @param <R>      主表主键、副表外键对应的数据类型
     * @param <S>      副表对应的实体类DO
     */
    public static <VO extends PkFieldValue<R, S>, R extends Serializable, S> void injectField(IPage<VO> voPage, IService<S> service, SFunction<S, R> fkColumn) {
        injectField(voPage.getRecords(), service, fkColumn);
    }


    /**
     * 此方法适用于一对多查询
     * <p>
     * 查询主表VO分页数据
     * <p>
     * 使用前先使用{@link EntityUtils#toPage(IPage, Function)}方法完成分页DO转分页VO
     * <p>
     * 主表VO需要实现{@link PkFieldValue}接口，接口中指定了主键的值和待注入字段的值
     * <p>
     * 此中方式API参数简洁 适合VO中注入一个字段，如果注入多个字段，请使用其它重返方法
     *
     * @param voPage       主表实体类VO的集合实例
     * @param serviceClazz 副表对应的{@link IService}实现类Class对象
     * @param fkColumn     副表实体类外键列（方法引用表示）
     * @param <VO>         主表实体类VO泛型 需要实现{@link PkFieldValue}接口
     * @param <R>          主表主键、副表外键对应的数据类型
     * @param <S>          副表对应的实体类DO
     */
    public static <VO extends PkFieldValue<R, S>, R extends Serializable, S> void injectField(IPage<VO> voPage, Class<? extends IService<S>> serviceClazz, SFunction<S, R> fkColumn) {
        injectField(voPage.getRecords(), getBean(serviceClazz), fkColumn);
    }
}

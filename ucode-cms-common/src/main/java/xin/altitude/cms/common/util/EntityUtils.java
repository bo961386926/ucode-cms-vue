/*
 *
 *  Copyright (c) 2020-2021, Java知识图谱 (http://www.altitude.xin).
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package xin.altitude.cms.common.util;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * EntityUtils工具类用于基于Lambda表达式实现类型转换，具有如下优点：
 * 1. 实现对象转对象；集合转集合；分页对象转分页对象
 * 2. 实体类转Vo、实体类转DTO等都能应用此工具类
 * 3. 转换参数均为不可变类型，业务更加安全
 *
 * @author explore
 * @since 2020/06/19 17:23
 **/
public class EntityUtils {
    /**
     * 将对象集合按照一定规则映射后收集为另一种形式的集合
     *
     * @param <R>       最终结果的泛型
     * @param <S>       原始集合元素的类泛型
     * @param <T>       转换后元素的中间状态泛型
     * @param <A>       最终结果收集器泛型
     * @param source    最原始的集合实例
     * @param action    转换规则
     * @param collector 收集器的类型
     * @return 变换后存储新元素的集合实例
     */
    public static <R, S, T, A> R collectList(final Collection<S> source, Function<? super S, ? extends T> action, Collector<? super T, A, R> collector) {
        Objects.requireNonNull(source);
        Objects.requireNonNull(collector);
        return source.stream().map(action).collect(collector);
    }
    
    /**
     * 将对象集合按照一定规则映射后收集为另一种形式的集合
     *
     * @param <S>    原始集合元素的类泛型
     * @param <T>    转换后元素的中间状态泛型
     * @param source 最原始的集合实例
     * @param action 转换规则
     * @return 变换后存储新元素的集合实例
     */
    public static <S, T> Set<T> collectSet(final Collection<S> source, Function<? super S, ? extends T> action) {
        Objects.requireNonNull(source);
        return source.stream().map(action).collect(Collectors.toSet());
    }
    
    /**
     * 将对象集合按照一定规则映射后收集为List集合
     *
     * @param <S>    原始集合元素的类泛型
     * @param source 最原始的集合实例
     * @param action 转换规则
     * @return 变换后存储新元素的集合实例
     */
    public static <S> List<? extends S> collectList(final Collection<S> source, Function<? super S, ? extends S> action) {
        return collectList(source, action, Collectors.toList());
    }
    
    /**
     * 将对象以一种类型转换成另一种类型
     *
     * @param <T>    源数据类型
     * @param <R>    变换后数据类型
     * @param source 源List集合
     * @param action 映射Lmabda表达式
     * @return 变换后的类型，如果source为null,则返回null
     */
    public static <T, R> R toObj(final T source, final Function<? super T, ? extends R> action) {
        Objects.requireNonNull(action);
        return Optional.ofNullable(source).map(action).orElse(null);
    }
    
    /**
     * 将List集合以一种类型转换成另一种类型
     *
     * @param <T>    源数据类型
     * @param <R>    变换后数据类型
     * @param source 源List集合
     * @param action 映射Lmabda表达式
     * @return 变换后的类型集合，如果source为null,则返回空集合
     */
    public static <T, R> List<R> toList(final Collection<T> source, final Function<? super T, ? extends R> action) {
        Objects.requireNonNull(action);
        if (Objects.nonNull(source)) {
            return source.stream().map(action).collect(Collectors.toList());
        }
        return new ArrayList<>();
    }
    
    // /**
    //  * 将IPaged对象以一种类型转换成另一种类型
    //  *
    //  * @param <T>    源数据类型
    //  * @param <R>    变换后数据类型
    //  * @param source 源IPage对象
    //  * @param action 映射Lmabda表达式
    //  * @return 变换后的类型集合，如果source为null,则返回null
    //  */
    // @SuppressWarnings("unchecked")
    // public static <T, R> IPage<R> convertPage(IPage<? extends T> source, final Function<? super T, ? extends R> action) {
    //     Optional<? extends IPage<? extends T>> optional = ofNullable(source);
    //     if (optional.isPresent()) {
    //         List<R> collect = source.getRecords().stream().map(action).collect(toList());
    //         return ((IPage<R>) source).setRecords(collect);
    //     }
    //     return null;
    // }
    
    /**
     * 将IPaged对象以一种类型转换成另一种类型
     *
     * @param source 源Page
     * @param action 转换规则
     * @param <E>    源Page类型泛型
     * @param <T>    源实体类
     * @param <R>    目标Page类型泛型
     * @return 变换后的分页类型
     */
    public static <E extends IPage<T>, T, R> IPage<R> toPage(E source, final Function<? super T, ? extends R> action) {
        Objects.requireNonNull(source);
        Objects.requireNonNull(action);
        return source.convert(action);
    }
    
    /**
     * 将集合转化成Map
     *
     * @param lists       集合实例
     * @param keyAction   key转换规则
     * @param valueAction value转换规则
     * @param <T>         集合实体类泛型
     * @param <K>         Key实体类泛型
     * @param <V>         Value实体类泛型
     * @return Map实例
     */
    public static <T, K, V> Map<K, V> toMap(final Collection<T> lists, Function<? super T, ? extends K> keyAction, Function<? super T, ? extends V> valueAction) {
        Objects.requireNonNull(lists);
        Objects.requireNonNull(keyAction);
        Objects.requireNonNull(valueAction);
        return lists.stream().collect(Collectors.toMap(keyAction, valueAction));
    }
    
    /**
     * 将List集合以一种类型转换成Set集合
     *
     * @param <T>    源数据类型
     * @param <R>    变换后数据类型
     * @param source 源List集合
     * @param action 映射Lmabda表达式
     * @return 变换后的类型集合，如果source为null,则返回空集合
     */
    public static <T, R> Set<R> toSet(final Collection<T> source, final Function<? super T, ? extends R> action) {
        Objects.requireNonNull(action);
        if (Objects.nonNull(source)) {
            return source.stream().map(action).collect(Collectors.toSet());
        }
        return new HashSet<>();
    }
}

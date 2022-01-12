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

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import java.util.Objects;
import java.util.function.Function;

/**
 * 谷歌Table工具类
 *
 * @author explore
 * @since 2019/12/01 19:32
 **/
public class TableUtils {
    
    /**
     * 将指定的实体类转化成谷歌Table
     *
     * @param <T>    源集合元素泛型
     * @param <R>    Table行元素泛型
     * @param <C>    Table列元素泛型
     * @param <V>    Table值元素泛型
     * @param source 源集合实例
     * @param r      行转换规则
     * @param c      列转换规则
     * @param v      值转换规则
     * @return Table实例
     */
    public static <T, R, C, V> Table<R, C, V> createHashTable(Iterable<T> source, Function<? super T, ? extends R> r, Function<? super T, ? extends C> c, Function<? super T, ? extends V> v) {
        Objects.requireNonNull(source);
        Objects.requireNonNull(r);
        Objects.requireNonNull(c);
        Objects.requireNonNull(v);
        Table<R, C, V> table = HashBasedTable.create();
        source.forEach(e -> table.put(EntityUtils.toObj(e, r), EntityUtils.toObj(e, c), EntityUtils.toObj(e, v)));
        return table;
    }
    
    /**
     * 将指定的实体类转化成谷歌Table
     *
     * @param source 源集合实例
     * @param r      行转换规则
     * @param c      列转换规则
     * @param <T>    源集合元素泛型
     * @param <R>    Table行元素泛型
     * @param <C>    Table列元素泛型
     * @return Table实例
     */
    public static <R, C, T> Table<R, C, T> createHashTable(Iterable<T> source, Function<? super T, ? extends R> r, Function<? super T, ? extends C> c) {
        return createHashTable(source, r, c, e -> e);
    }
}

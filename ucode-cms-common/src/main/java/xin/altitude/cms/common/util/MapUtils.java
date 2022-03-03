
/*
 *
 * Copyright (c) 2020-2022, Java知识图谱 (http://www.altitude.xin).
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

package xin.altitude.cms.common.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

/**
 * MapUtils工具类
 * 用以简化多个key值的map取值操作
 * 提供处理Map多key取值工具方法
 *
 * @author explore
 * @since 2019/06/03 22:49
 **/
public class MapUtils {
    /**
     * 批量取出Map中的值
     *
     * @param map  map
     * @param keys 键的集合
     * @param <K>  key的泛型
     * @param <V>  value的泛型
     * @return value的泛型的集合
     */
    @SafeVarargs
    public static <K, V> List<V> getCollection(Map<K, V> map, K... keys) {
        Objects.requireNonNull(keys);
        return getCollection(map, Arrays.asList(keys));
    }

    /**
     * 批量取出Map中的值
     *
     * @param map  map
     * @param keys 键的集合
     * @param <K>  key的泛型
     * @param <V>  value的泛型
     * @return value的泛型的集合
     */
    public static <K, V> List<V> getCollection(Map<K, V> map, Iterable<K> keys) {
        List<V> result = new ArrayList<>();
        if (map != null && !map.isEmpty() && keys != null) {
            keys.forEach(key -> Optional.ofNullable(map.get(key)).ifPresent(result::add));
        }
        return result;
    }

    /**
     * 批量取出Map中的值
     *
     * @param map        map实例
     * @param keys       键key集合
     * @param comparator 排序器
     * @param <K>        key的泛型
     * @param <V>        value的泛型
     * @return value的泛型的集合
     */
    public static <K, V> List<V> getCollection(Map<K, V> map, Iterable<K> keys, Comparator<V> comparator) {
        Objects.requireNonNull(comparator);
        List<V> result = getCollection(map, keys);
        result.sort(comparator);
        return result;
    }
}

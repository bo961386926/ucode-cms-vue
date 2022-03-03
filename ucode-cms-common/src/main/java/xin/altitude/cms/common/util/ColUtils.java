
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
import java.util.Collection;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 集合工具类
 *
 * @author explore
 * @since 2019/06/03 23:17
 **/
public class ColUtils {
    /**
     * 将单个对象转化为集合
     *
     * @param e   对象实例
     * @param <E> 对象类型
     * @return 包含对象的集合实例
     */
    public static <E> Collection<E> toCol(E e) {
        return toCol(e, ArrayList::new);
    }

    /**
     * 将单个对象转化为集合
     *
     * @param t        对象实例
     * @param supplier 集合工厂
     * @param <E>      对象类型
     * @param <C>      集合类型
     * @return 包含对象的集合实例
     */
    public static <E, C extends Collection<E>> Collection<E> toCol(E t, Supplier<C> supplier) {
        return Stream.of(t).collect(Collectors.toCollection(supplier));
    }

    /**
     * 取出集合中第一个元素
     *
     * @param collection 集合实例
     * @param <E>        集合中元素类型
     * @return 泛型类型
     */
    public static <E> E toObj(Collection<E> collection) {
        // 处理集合空指针异常
        Collection<E> coll = Optional.ofNullable(collection).orElseGet(ArrayList::new);
        // 此处可以对流进行排序，然后取出第一个元素
        return coll.stream().findFirst().orElse(null);
    }
}

/*
 *
 * Copyright (c) 2020-2024, 赛泰先生 (http://www.altitude.xin).
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

/**
 * Jackson工具类
 * 需要运行在Spring生态中
 *
 * @author <a href="http://www.altitude.xin" target="_blank">赛泰先生</a>
 * @author <a href="https://gitee.com/decsa/ucode-cms-vue" target="_blank">UCode CMS</a>
 * @author <a href="https://space.bilibili.com/1936685014" target="_blank">B站视频</a>
 * @since 2021/03/24 13:39
 **/
public abstract class JacksonUtils {
    private final static Logger log = LoggerFactory.getLogger(JacksonUtils.class);
    // /**
    //  * 从Spring容器中获取ObjectMapper实例
    //  */
    // private final static ObjectMapper OBJECT_MAPPER = SpringUtils.getBean(ObjectMapper.class);

    /**
     * 把JavaBean转换为json字符串 抛出异常
     * 本方法是{@link JacksonUtils#writeValueAsString}的别名方法
     */
    public static String writeValue(final ObjectMapper mapper, final Object obj) {
        return writeValueAsString(mapper, obj);
    }


//    /**
//     * 将对象JSON序列化为字符串
//     *
//     * @param obj 对象
//     * @return JSON字符串
//     */
//    public static String writeValueAsString(Object obj) {
//        return writeValueAsString(SpringUtils.getBean(ObjectMapper.class), obj);
//    }

    /**
     * 将对象JSON序列化为字符串
     *
     * @param obj 对象
     * @return JSON字符串
     */
    public static String writeValueAsString(final ObjectMapper mapper, final Object obj) {
        ObjectMapper m = Optional.ofNullable(mapper).orElseGet(() -> SpringUtils.getBean(ObjectMapper.class));
        try {
            if (obj instanceof String) {
                return (String) obj;
            }
            return m.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


//    /**
//     * 将对象JSON序列化成字节数组
//     */
//    public static byte[] writeValueAsBytes(Object obj) {
//        return writeValueAsBytes(SpringUtils.getBean(ObjectMapper.class), obj);
//    }

    /**
     * 将对象JSON序列化成字节数组
     */
    public static byte[] writeValueAsBytes(ObjectMapper mapper, Object obj) {
        ObjectMapper m = Optional.ofNullable(mapper).orElseGet(() -> SpringUtils.getBean(ObjectMapper.class));
        try {
            if (obj instanceof String) {
                return ((String) obj).getBytes(StandardCharsets.UTF_8);
            }
            return m.writeValueAsBytes(obj);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 将json字符串反序列化成对象
     * 本方法是{@link JacksonUtils#readObjectValue}别名方法
     *
     * @param jsonValue jsonValue
     * @param clazz     class
     * @param <T>       T 泛型标记
     * @return Bean
     */
    public static <T> T parse(final ObjectMapper mapper, final String jsonValue, final Class<T> clazz) {
        return readObjectValue(mapper, jsonValue, clazz);
    }

    /**
     * 将json字符串反序列化成对象
     * 本方法是{@link JacksonUtils#readObjectValue}别名方法
     *
     * @param jsonValue jsonValue
     * @param clazz     class
     * @param <T>       T 泛型标记
     * @return Bean
     */
    public static <T> T readValue(final ObjectMapper mapper, final String jsonValue, final Class<T> clazz) {
        return readObjectValue(mapper, jsonValue, clazz);
    }

//    /**
//     * 读取{@link List}集合类型JSON字符串
//     *
//     * @param jsonValue
//     * @param collClazz
//     * @param elementClazz
//     * @param <T>
//     * @return
//     */
//    public static <T> List<T> readValue(String jsonValue, Class<? extends List> collClazz, Class<T> elementClazz) {
//        return readListValue(null, jsonValue, elementClazz);
//    }


//    /**
//     * 将json字符串反序列化成对象
//     *
//     * @param jsonValue jsonValue
//     * @param clazz     class
//     * @param <T>       T 泛型标记
//     * @return Bean
//     */
//    public static <T> T readObjectValue(String jsonValue, Class<T> clazz) {
//        return readObjectValue(SpringUtils.getBean(ObjectMapper.class), jsonValue, clazz);
//    }


    /**
     * 将json字符串反序列化成对象
     *
     * @param jsonValue jsonValue
     * @param clazz     class
     * @param <T>       T 泛型标记
     * @return Bean
     */
    public static <T> T readObjectValue(final ObjectMapper mapper, final String jsonValue, final Class<T> clazz) {
        Assert.notNull(clazz, "clazz参数不能为空");
        ObjectMapper m = Optional.ofNullable(mapper).orElseGet(() -> SpringUtils.getBean(ObjectMapper.class));
        try {
            if (jsonValue != null) {
                return m.readValue(jsonValue, clazz);
            } else {
                return clazz.newInstance();
            }
        } catch (IOException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }


//    public static <T> T readObjectValue(byte[] bytes, Class<T> clazz) {
//        return readObjectValue(SpringUtils.getBean(ObjectMapper.class), bytes, clazz);
//    }

    public static <T> T readObjectValue(final ObjectMapper mapper, byte[] bytes, Class<T> clazz) {
        ObjectMapper m = Optional.ofNullable(mapper).orElseGet(() -> SpringUtils.getBean(ObjectMapper.class));
        return Optional.ofNullable(bytes).map(f -> {
            try {
                return m.readValue(f, clazz);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).orElse(null);
    }


//    public static <T> T readObjectValue(InputStream is, Class<T> clazz) {
//        return readObjectValue(SpringUtils.getBean(ObjectMapper.class), is, clazz);
//    }

    public static <T> T readObjectValue(final ObjectMapper mapper, InputStream is, Class<T> clazz) {
        Assert.notNull(is, "is参数不能为空");
        Assert.notNull(clazz, "clazz参数不能为空");
        ObjectMapper m = Optional.ofNullable(mapper).orElseGet(() -> SpringUtils.getBean(ObjectMapper.class));
        try {
            if (is.available() != 0) {
                return m.readValue(is, clazz);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }


//    public static <T> T readObjectValue(String jsonValue, JavaType javaType) {
//        return readObjectValue(SpringUtils.getBean(ObjectMapper.class), jsonValue, javaType);
//    }

    public static <T> T readObjectValue(final ObjectMapper mapper, String jsonValue, JavaType javaType) {
        if (StringUtils.hasLength(jsonValue)) {
            try {
                return mapper.readValue(jsonValue, javaType);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }


    /**
     * 读取{@link List}集合类型JSON字符串
     *
     * @param jsonValue
     * @param elementClazz
     * @param <T>
     * @return
     */
    public static <T> List<T> readListValue(final ObjectMapper mapper, String jsonValue, Class<T> elementClazz) {
        Assert.notNull(elementClazz, "elementClazz参数不能为空");
        if (jsonValue == null) {
            return Collections.emptyList();
        }
        return readObjectValue(mapper, jsonValue, getJavaType(null, List.class, elementClazz));
    }


//    /**
//     * 获取{@link JavaType}对象
//     *
//     * @param collectionClazz 集合Class对象
//     * @param elementClazz    元素Class对象
//     * @param <T>             元素类型
//     * @return {@link JavaType}对象实例
//     */
//    public static <T> JavaType getJavaType(Class<? extends Collection> collectionClazz, Class<T> elementClazz) {
//        return getJavaType(SpringUtils.getBean(ObjectMapper.class), collectionClazz, elementClazz);
//    }

    /**
     * 获取{@link JavaType}对象
     *
     * @param collectionClazz 集合Class对象
     * @param elementClazz    元素Class对象
     * @param <T>             元素类型
     * @return {@link JavaType}对象实例
     */
    public static <T> JavaType getJavaType(final ObjectMapper mapper, Class<? extends Collection> collectionClazz, Class<T> elementClazz) {
        ObjectMapper m = Optional.ofNullable(mapper).orElseGet(() -> SpringUtils.getBean(ObjectMapper.class));
        return m.getTypeFactory().constructCollectionType(List.class, elementClazz);
    }

//    /**
//     * 读取{@link List}集合类型JSON字符串
//     *
//     * @param bytes
//     * @param elementClazz
//     * @param <T>
//     * @return
//     */
//    public static <T> List<T> readListValue(byte[] bytes, Class<T> elementClazz) {
//        return readListValue(SpringUtils.getBean(ObjectMapper.class), bytes, elementClazz);
//    }

    /**
     * 读取{@link List}集合类型JSON字符串
     *
     * @param bytes
     * @param elementClazz
     * @param <T>
     * @return
     */
    public static <T> List<T> readListValue(final ObjectMapper mapper, byte[] bytes, Class<T> elementClazz) {
        ObjectMapper m = Optional.ofNullable(mapper).orElseGet(() -> SpringUtils.getBean(ObjectMapper.class));
        JavaType javaType = m.getTypeFactory().constructCollectionType(List.class, elementClazz);
        if (javaType != null) {
            try {
                return m.readValue(bytes, javaType);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return Collections.emptyList();
    }

//    public static <T> List<T> readListValue(InputStream is, Class<T> elementClazz) {
//        return readListValue(SpringUtils.getBean(ObjectMapper.class), is, elementClazz);
//    }

    public static <T> List<T> readListValue(final ObjectMapper mapper, InputStream is, Class<T> elementClazz) {
        ObjectMapper m = Optional.ofNullable(mapper).orElseGet(() -> SpringUtils.getBean(ObjectMapper.class));
        JavaType javaType = m.getTypeFactory().constructCollectionType(List.class, elementClazz);
        try {
            if (is.available() != 0 && javaType != null) {
                return m.readValue(is, javaType);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    /**
     * 使用JSON序列化的方式克隆对象
     * 此方式对原始对象无任何要求，属于深拷贝
     *
     * @param obj   原始对象实例
     * @param clazz 原始对象类对象
     * @param <T>   原始对象泛型
     * @return 原始对象深拷贝实例
     */
    public static <T> T clone(T obj, Class<T> clazz) {
        return readObjectValue(null, writeValue(null, obj), clazz);
    }
}

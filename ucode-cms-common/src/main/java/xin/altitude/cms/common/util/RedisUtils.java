/*
 *
 * Copyright (c) 2020-2022, 赛泰先生 (http://www.altitude.xin).
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

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import xin.altitude.cms.common.constant.RedisConstants;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * {@link RedisUtils}工具类
 *
 * @author <a href="http://www.altitude.xin" target="_blank">赛泰先生</a>
 * @author <a href="https://gitee.com/decsa/ucode-cms-vue" target="_blank">UCode CMS</a>
 * @author <a href="https://space.bilibili.com/1936685014" target="_blank">B站视频</a>
 * @since 1.6.5
 */
public class RedisUtils {
    private static final Logger logger = LoggerFactory.getLogger(RedisUtils.class);


    private static final StringRedisTemplate TEMPLATE = SpringUtils.getBean(StringRedisTemplate.class);

    /**
     * <p>缓存基本的对象</p>
     *
     * @param id       缓存的主键ID用以形成唯一key
     * @param value    缓存的值
     * @param timeout  过期时间（小于或者等于0 表示缓存用不过期）
     * @param timeUnit 时间颗粒度
     */
    public static <T> void save(final Serializable id, final T value, final long timeout, final TimeUnit timeUnit) {
        String key = RedisConstants.createCacheKey(value.getClass(), id);
        save(TEMPLATE, key, value, timeout, timeUnit);
    }

    /**
     * <p>缓存基本的对象，Integer、String、实体类等 如果value是String类型，直接直接保存 如果不是，则转化序列化承JSON字符串后保存</p>
     * <p>支持指定过期时间</p>
     * <p>缓存对象值支持多种类型：普通单个实体类对象、{@link List}实体类集合、{@link IPage}分页对象、{@link Map}实例对象</p>
     *
     * @param key      缓存的键值
     * @param value    缓存的值
     * @param timeout  过期时间（小于或者等于0 表示缓存用不过期）
     * @param timeUnit 时间颗粒度
     */
    public static void save(final String key, final Object value, final long timeout, final TimeUnit timeUnit) {
        save(TEMPLATE, key, value, timeout, timeUnit);
    }

    /**
     * <p>缓存基本的对象，Integer、String、实体类等 如果value是String类型，直接直接保存 如果不是，则转化序列化承JSON字符串后保存</p>
     * <p>支持指定过期时间</p>
     * <p>缓存对象值支持多种类型：普通单个实体类对象、{@link List}实体类集合、{@link IPage}分页对象、{@link Map}实例对象</p>
     *
     * @param key      缓存的键值
     * @param value    缓存的值
     * @param timeout  过期时间（小于或者等于0 表示缓存用不过期）
     * @param timeUnit 时间颗粒度
     */
    public static void save(final StringRedisTemplate template, final String key, final Object value, final long timeout, final TimeUnit timeUnit) {
        if (value instanceof String) {
            if (timeout <= 0) {
                template.opsForValue().set(key, (String) value);
                logger.info("object save successful,key:{},value:{},no expire time", key, value);
            } else {
                template.opsForValue().set(key, (String) value, timeout, timeUnit);
                logger.info("object save successful,key:{},value:{}", key, value);
            }
        } else {
            String writeValue = JacksonUtils.writeValue(value);
            Optional<String> stringOptional = Optional.ofNullable(writeValue);
            if (timeout <= 0) {
                stringOptional.ifPresent(e -> template.opsForValue().set(key, e));
                logger.info("object save successful,key:{},value:{},no expire time", key, writeValue);
            } else {
                stringOptional.ifPresent(e -> template.opsForValue().set(key, e, timeout, timeUnit));
                logger.info("object save successful,key:{},value:{}", key, writeValue);
            }
        }
    }

    // /**
    //  * 批量保存数据
    //  */
    // public static <V> void saveBatch(final Map<String, V> map) {
    //     Map<String, String> transMap = MapUtils.transMap(map, JacksonUtils::writeValue);
    //     OPS_FOR_VALUE.multiSet(transMap);
    // }

    /**
     * 批量保存数据 设置过期时间
     */
    public static <V> void saveBatch(final Map<String, V> map, int timeout, TimeUnit timeUnit) {
        saveBatch(TEMPLATE, map, timeout, timeUnit);
    }

    /**
     * 批量保存数据 设置过期时间
     */
    public static <V> void saveBatch(StringRedisTemplate template, final Map<String, V> map, int timeout, TimeUnit timeUnit) {
        Map<String, String> transMap = MapUtils.transMap(map, JacksonUtils::writeValue);
        template.opsForValue().multiSet(transMap);

        if (timeUnit.equals(TimeUnit.SECONDS)) {
            RedisAsyncUtils.expire(map.keySet(), timeout);
        } else if (timeUnit.equals(TimeUnit.MICROSECONDS)) {
            RedisAsyncUtils.pExpire(map.keySet(), timeout);
        }
    }


    /**
     * 设置有效时间
     *
     * @param key     Redis键
     * @param timeout 超时时间
     * @param unit    时间单位
     * @return true=设置成功；false=设置失败
     */
    public static Boolean expire(final String key, final Long timeout, final TimeUnit unit) {
        return expire(TEMPLATE, key, timeout, unit);
    }

    /**
     * 设置有效时间
     *
     * @param key     Redis键
     * @param timeout 超时时间
     * @param unit    时间单位
     * @return true=设置成功；false=设置失败
     */
    public static Boolean expire(StringRedisTemplate template, final String key, final Long timeout, final TimeUnit unit) {
        return template.expire(key, timeout, unit);
    }

    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public static <T> T getObject(final String key, final Class<T> clazz) {
        return getObject(TEMPLATE, key, clazz);
    }

    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public static <T> T getObject(StringRedisTemplate template, final String key, final Class<T> clazz) {
        return Optional.ofNullable(template.opsForValue().get(key)).map(e -> JacksonUtils.readObjectValue(e, clazz)).orElse(null);
    }

    /**
     * 获得缓存的基本对象。
     *
     * @param id 主键值 使用{@link RedisConstants#createCacheKey(Class, Serializable)}转换成Key
     * @return 缓存键值对应的数据
     */
    public static <T> T getObject(final Serializable id, final Class<T> clazz) {
        String key = RedisConstants.createCacheKey(clazz, id);
        return getObject(key, clazz);
    }

    /**
     * 获得缓存的基本对象。
     *
     * @param id 主键值 使用{@link RedisConstants#createCacheKey(Class, Serializable)}转换成Key
     * @return 缓存键值对应的数据
     */
    public static <T> T getObject(StringRedisTemplate template, final Serializable id, final Class<T> clazz) {
        String key = RedisConstants.createCacheKey(clazz, id);
        return getObject(template, key, clazz);
    }


    /**
     * 获得缓存的基本对象。
     *
     * @param id 主键值 使用{@link RedisConstants#createCacheKey(Class, Serializable)}转换成Key
     * @return 缓存键值对应的数据
     */
    public static <T> T getObject(final Serializable id, final long timeout, final TimeUnit timeUnit, final Class<T> clazz, Function<Serializable, T> action) {
        return getObject(TEMPLATE, id, timeout, timeUnit, clazz, action);
    }


    /**
     * 获得缓存的基本对象。
     * <p>
     * 使用DCL技术避免缓存穿透
     *
     * @param id 主键值 使用{@link RedisConstants#createCacheKey(Class, Serializable)}转换成Key
     * @return 缓存键值对应的数据
     */
    public static <T> T getObject(final StringRedisTemplate template, final Serializable id, final long timeout, final TimeUnit timeUnit, final Class<T> clazz, Function<Serializable, T> action) {
        String key = RedisConstants.createCacheKey(clazz, id);
        T t = getObject(template, key, clazz);
        if (t == null) {
            synchronized (RedisConstants.createLockKey(clazz, id)) {
                T t2 = getObject(template, key, clazz);
                if (t2 == null) {
                    save(template, key, action.apply(id), timeout, timeUnit);
                    return getObject(template, key, clazz);
                }
                return t2;
            }
        } else {
            return t;
        }
    }


    /**
     * 将JSON字符串转化成集合对象
     *
     * @param keys
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> getObjects(final Class<T> clazz, final Collection<String> keys) {
        return getObjects(TEMPLATE, clazz, keys);
    }

    /**
     * 将JSON字符串转化成集合对象
     *
     * @param keys
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> getObjects(StringRedisTemplate template, final Class<T> clazz, final Collection<String> keys) {
        List<String> valueString = template.opsForValue().multiGet(keys);
        return Optional.ofNullable(valueString).map(e -> EntityUtils.toList(e, f -> JacksonUtils.readObjectValue(f, clazz))).orElse(Collections.emptyList());
    }


    /**
     * 将JSON字符串转化成集合对象
     *
     * @param ids
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> getObjects(final Collection<? extends Serializable> ids, final Class<T> clazz) {
        return getObjects(TEMPLATE, ids, clazz);
    }


    /**
     * 将JSON字符串转化成集合对象
     *
     * @param ids
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> getObjects(StringRedisTemplate template, final Collection<? extends Serializable> ids, final Class<T> clazz) {
        List<String> keys = EntityUtils.toList(ids, e -> RedisConstants.createCacheKey(clazz, e));
        return getObjects(template, clazz, keys);
    }


    /**
     * 将JSON字符串转化成集合对象
     *
     * @param ids
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> getObjects(final Collection<Serializable> ids, long timeout, TimeUnit unit, final Class<T> clazz, Function<Collection<Serializable>, List<T>> action) {
        return getObjects(TEMPLATE, ids, timeout, unit, clazz, action);
    }


    /**
     * 将JSON字符串转化成集合对象
     *
     * @param ids
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> getObjects(StringRedisTemplate template, final Collection<Serializable> ids, long timeout, TimeUnit unit, final Class<T> clazz, Function<Collection<Serializable>, List<T>> action) {
        List<T> result = new ArrayList<>(ids.size());
        List<Serializable> remainIds = new ArrayList<>();
        for (Serializable id : ids) {
            // String key = RedisConstants.createCacheKey(clazz, id);
            T t = getObject(template, id, clazz);
            if (t == null) {
                remainIds.add(id);
            }
            result.add(t);
        }

        if (remainIds.size() > 0) {
            for (Serializable id : remainIds) {
                T t = getObject(template, id, clazz);
                if (t == null) {
                    synchronized (RedisConstants.createLockKey(clazz, id)) {
                        T t2 = ColUtils.toObj(action.apply(Collections.singletonList(id)));
                        if (t2 != null) {
                            save(template, RedisConstants.createCacheKey(clazz, id), t2, timeout, unit);
                            result.add(t2);
                        }
                    }
                }
                result.add(t);

            }
        }

        return result;
    }


    /**
     * 将JSON字符串转化成集合对象
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <D, T> List<T> getCollection(D d, final Class<T> clazz) {
        return getCollection(TEMPLATE, d, clazz);
    }


    /**
     * 将JSON字符串转化成集合对象
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <PO, T> List<T> getCollection(StringRedisTemplate template, PO po, final Class<T> clazz) {
        String poStr = JacksonUtils.writeValueAsString(po);
        String key = RedisConstants.createCacheKey(clazz, MD5Utils.md5(poStr));
        String jsonValue = template.opsForValue().get(key);
        return Optional.ofNullable(jsonValue).map(e -> JacksonUtils.readListValue(e, clazz)).orElse(Collections.emptyList());
    }


    /**
     * 通过PO封装查询条件，使用普通索引查询多条记录（结果集为集合）
     * <p>
     * 如果仅涉及主键查询，请使用{@link RedisUtils#getObject(StringRedisTemplate, Serializable, long, TimeUnit, Class, Function)}或者{@link RedisUtils#getObjects(StringRedisTemplate, Collection, long, TimeUnit, Class, Function)}
     * <p>
     * 使用JVM本地锁处理缓存
     *
     * @param d       封装的查询对象
     * @param timeout 过期时间（小于或者等于0 表示缓存不过期）
     * @param unit    过期时间单位
     * @param clazz   与表一一对应的实体类CLass对象
     * @param action  访问数据库方法
     * @param <D>     封装查询条件类
     * @param <T>     与表一一对应的实体类
     * @return 集合
     */
    public static <D, T> List<T> getCollection(D d, long timeout, TimeUnit unit, final Class<T> clazz, Function<D, List<T>> action) {
        return getCollection(TEMPLATE, d, timeout, unit, clazz, action);
    }


    /**
     * 通过PO封装查询条件，使用普通索引查询多条记录（结果集为集合）
     * <p>
     * 如果仅涉及主键查询，请使用{@link RedisUtils#getObject(StringRedisTemplate, Serializable, long, TimeUnit, Class, Function)}或者{@link RedisUtils#getObjects(StringRedisTemplate, Collection, long, TimeUnit, Class, Function)}
     * <p>
     * 使用JVM本地锁处理缓存
     *
     * @param template 如果不需要自定义此参数，可忽略此参数，自动从容器中获取值
     * @param d        封装的查询对象
     * @param timeout  过期时间（小于或者等于0 表示缓存不过期）
     * @param unit     过期时间单位
     * @param clazz    与表一一对应的实体类CLass对象
     * @param action   访问数据库方法
     * @param <D>      封装查询条件类
     * @param <T>      与表一一对应的实体类
     * @return 集合
     */
    public static <D, T> List<T> getCollection(StringRedisTemplate template, D d, long timeout, TimeUnit unit, final Class<T> clazz, Function<D, List<T>> action) {
        String poStr = JacksonUtils.writeValueAsString(d);
        String key = RedisConstants.createCacheKey(clazz, MD5Utils.md5(poStr));

        List<T> tList = getCollection(template, d, clazz);
        if (tList == null || tList.size() == 0) {
            synchronized (RedisConstants.createLockKey(clazz, MD5Utils.md5(poStr))) {
                List<T> tList2 = getCollection(template, d, clazz);
                if (tList2 == null || tList2.size() == 0) {
                    save(template, key, action.apply(d), timeout, unit);
                    return getCollection(template, d, clazz);
                }
                return tList2;
            }
        }
        return tList;
    }

    // public static <D, T> List<T> getCollection(StringRedisTemplate template, D d, final Class<T> clazz) {
    //     String poStr = JacksonUtils.writeValueAsString(d);
    //     String key = RedisConstants.createCacheKey(clazz, MD5Utils.md5(poStr));
    //
    //     List<T> tList = getCollection(template, d, clazz);
    //     if (tList == null || tList.size() == 0) {
    //         synchronized (RedisConstants.createLockKey(clazz, MD5Utils.md5(poStr))) {
    //             List<T> tList2 = getCollection(template, d, clazz);
    //             if (tList2 == null || tList2.size() == 0) {
    //                 save(template, key, action.apply(d), timeout, unit);
    //                 return getCollection(template, d, clazz);
    //             }
    //             return tList2;
    //         }
    //     }
    //     return tList;
    // }

    /**
     * 找出差异值 类型不确定 具体实现还是挺复杂的
     */
    private static <T> Collection<? extends Serializable> getRemainIds(Collection<? extends Serializable> ids, List<T> ts, TableInfo tableInfo) {
        Serializable id = ColUtils.toObj(ids);
        if (id instanceof String) {
            List<String> tsIds = EntityUtils.toList(ts, e -> pkVal(tableInfo, e));
            List<String> remainIds = EntityUtils.toList(ids, e -> (String) e);
            remainIds.removeAll(tsIds);
            return remainIds;
        } else if (id instanceof Number) {
            List<String> tsIds = EntityUtils.toList(ts, e -> String.format("%s", (Serializable) pkVal(tableInfo, e)));
            List<String> remainIds = EntityUtils.toList(ids, String::valueOf);
            remainIds.removeAll(tsIds);
            return remainIds;
        }

        return Collections.emptyList();
    }


    /**
     * 获取当前DO实体类主键值
     *
     * @param tableInfo 表信息实例
     * @param e         DO实体类
     * @param <E>       DO实体类类型
     * @param <R>       主键的类型
     * @return 主键值
     */
    @SuppressWarnings("unchecked")
    public static <E, R> R pkVal(TableInfo tableInfo, E e) {
        String keyProperty = tableInfo.getKeyProperty();
        return (R) tableInfo.getPropertyValue(e, keyProperty);
    }


    /**
     * 删除单个对象
     *
     * @param key
     */
    public static Boolean remove(final String key) {
        return remove(TEMPLATE, key);
    }

    /**
     * 删除单个对象
     *
     * @param key
     */
    public static Boolean remove(StringRedisTemplate template, final String key) {
        return template.delete(key);
    }

    /**
     * 删除集合对象
     *
     * @param keys 多个Key
     * @return
     */
    public static Long remove(final Collection<String> keys) {
        return remove(TEMPLATE, keys);
    }

    /**
     * 删除集合对象
     *
     * @param keys 多个Key
     * @return
     */
    public static Long remove(StringRedisTemplate template, final Collection<String> keys) {
        return template.delete(keys);
    }

    /**
     * 批量保存
     *
     * @param map
     */
    public static void multiSet(Map<String, String> map, long timeout, TimeUnit unit) {
        multiSet(TEMPLATE, map, timeout, unit);
    }

    /**
     * 批量保存
     *
     * @param map
     */
    public static void multiSet(StringRedisTemplate template, Map<String, String> map, long timeout, TimeUnit unit) {
        template.opsForValue().multiSet(map);
    }


    /**
     * 批量保存
     *
     * @param map
     * @param clazz
     * @param <T>
     */
    public static <T> void multiSet(Map<String, T> map, Class<T> clazz, long timeout, TimeUnit unit) {
        multiSet(TEMPLATE, map, clazz, timeout, unit);
    }

    /**
     * 批量保存
     *
     * @param map
     * @param clazz
     * @param <T>
     */
    public static <T> void multiSet(StringRedisTemplate template, Map<String, T> map, Class<T> clazz, long timeout, TimeUnit unit) {
        Map<String, String> stringMap = MapUtils.transMap(map, e -> e, JacksonUtils::writeValue);
        template.opsForValue().multiSet(stringMap);
    }

    /**
     * 批量查询Key对应的Value值
     *
     * @param keys 批量Keys
     * @return Value值集合
     */
    public static List<String> multiGet(StringRedisTemplate template, List<String> keys) {
        return EntityUtils.toList(template.opsForValue().multiGet(keys), Function.identity(), Objects::nonNull);
    }


    /**
     * 批量查询Key对应的Value值
     *
     * @param keys 批量Keys
     * @return Value值集合
     */
    public static <T> List<T> multiGet(List<String> keys, Class<T> clazz) {
        return multiGet(TEMPLATE, keys, clazz);
    }

    /**
     * 批量查询Key对应的Value值
     *
     * @param keys 批量Keys
     * @return Value值集合
     */
    public static <T> List<T> multiGet(StringRedisTemplate template, List<String> keys, Class<T> clazz) {
        return EntityUtils.toList(multiGet(template, keys), e -> JacksonUtils.readObjectValue(e, clazz), Objects::nonNull);
    }


    /**
     * 向channel发布消息
     * 如果消息是String类型，直接发送消息；
     * 如果不是，则转化序列化承JSON后发送消息
     *
     * @param channelName channel名称
     * @param msg         消息
     * @since 1.4.5
     */
    public static <T> void publishMsg(final String channelName, T msg) {
        publishMsg(TEMPLATE, channelName, msg);
    }

    /**
     * 向channel发布消息
     * 如果消息是String类型，直接发送消息；
     * 如果不是，则转化序列化承JSON后发送消息
     *
     * @param channelName channel名称
     * @param msg         消息
     * @since 1.4.5
     */
    public static <T> void publishMsg(StringRedisTemplate template, final String channelName, T msg) {
        if (msg instanceof String) {
            template.convertAndSend(channelName, msg);
        } else {
            /* 现将对象格式化为JSON字符串，然后保存 */
            String json = JacksonUtils.writeValueAsString(msg);
            Optional.ofNullable(json).ifPresent(e -> template.convertAndSend(channelName, e));
        }
    }
}

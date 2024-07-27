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

import com.baomidou.mybatisplus.core.metadata.IPage;
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
 * @author <a href="https://space.bilibili.com/1936685014" target="_blank">赛泰先生的B站视频</a>
 * @since 1.6.5
 */
public abstract class RedisUtils {
    private static final Logger log = LoggerFactory.getLogger(RedisUtils.class);


    /**
     * <p>缓存基本的对象</p>
     *
     * @param template 如无特别需求，请设置为<code>null</code>
     * @param id       缓存的主键ID用以形成唯一key
     * @param value    缓存的值
     * @param timeout  过期时间（小于或者等于0 表示缓存用不过期）
     * @param timeUnit 时间颗粒度
     */
    public static <T> void save(final StringRedisTemplate template, final Serializable id, final T value, final long timeout, final TimeUnit timeUnit) {
        final String key;
        if (id instanceof String) {
            key = RedisConstants.createCacheKey(value.getClass(), (String) id);
        } else if (id instanceof Long) {
            key = RedisConstants.createCacheKey(value.getClass(), (Long) id);
        } else if (id instanceof Integer) {
            key = RedisConstants.createCacheKey(value.getClass(), (Integer) id);
        } else {
            throw new IllegalArgumentException("参数id类型不正确");
        }
        save(template, key, value, timeout, timeUnit);
    }

    /**
     * <p>缓存基本的对象，Integer、String、实体类等 如果value是String类型，直接直接保存 如果不是，则转化序列化承JSON字符串后保存</p>
     * <p>支持指定过期时间</p>
     * <p>缓存对象值支持多种类型：普通单个实体类对象、{@link List}实体类集合、{@link IPage}分页对象、{@link Map}实例对象</p>
     *
     * @param template 如果为<code>null</code> 则自动从容器中获取
     * @param key      缓存的键值
     * @param value    缓存的值
     * @param timeout  过期时间（小于或者等于0 表示缓存用不过期）
     * @param timeUnit 时间颗粒度
     */
    public static void save(final StringRedisTemplate template, final String key, final Object value, final long timeout, final TimeUnit timeUnit) {
        StringRedisTemplate t = Optional.ofNullable(template).orElseGet(() -> SpringUtils.getBean(StringRedisTemplate.class));

        String writeValue = JacksonUtils.writeValue(null, value);
        Optional<String> stringOptional = Optional.ofNullable(writeValue);
        if (timeout <= 0) {
            stringOptional.ifPresent(e -> t.opsForValue().set(key, e));
            log.debug("object save successful,key:{},value:{},no expire time", key, writeValue);
        } else {
            stringOptional.ifPresent(e -> t.opsForValue().set(key, e, timeout, timeUnit));
            log.debug("object save successful,key:{},value:{}", key, writeValue);
        }
    }

    /**
     * @param template 如无特别需求，请设置为<code>null</code>
     *                 批量保存数据 设置过期时间
     */
    public static <V> void saveBatch(final StringRedisTemplate template, final Map<String, V> map, int timeout, TimeUnit timeUnit) {
        StringRedisTemplate t = Optional.ofNullable(template).orElseGet(() -> SpringUtils.getBean(StringRedisTemplate.class));
        Map<String, String> transMap = MapUtils.transMap(map, e -> JacksonUtils.writeValue(null, e));
        t.opsForValue().multiSet(transMap);

        if (timeUnit.equals(TimeUnit.SECONDS)) {
            RedisAsyncUtils.expire(map.keySet(), timeout);
        } else if (timeUnit.equals(TimeUnit.MICROSECONDS)) {
            RedisAsyncUtils.pExpire(map.keySet(), timeout);
        }
    }

    /**
     * 设置有效时间
     *
     * @param template 如无特别需求，请设置为<code>null</code>
     * @param key      Redis键
     * @param timeout  超时时间
     * @param unit     时间单位
     * @return true=设置成功；false=设置失败
     */
    public static Boolean expire(final StringRedisTemplate template, final String key, final Long timeout, final TimeUnit unit) {
        StringRedisTemplate t = Optional.ofNullable(template).orElseGet(() -> SpringUtils.getBean(StringRedisTemplate.class));
        return t.expire(key, timeout, unit);
    }

    /**
     * 获得缓存的基本对象。
     *
     * @param template 如无特别需求，请设置为<code>null</code>
     * @param key      缓存键值
     * @return 缓存键值对应的数据
     */
    public static <T> T getObject(final StringRedisTemplate template, final String key, final Class<T> clazz) {
        StringRedisTemplate t = Optional.ofNullable(template).orElseGet(() -> SpringUtils.getBean(StringRedisTemplate.class));
        return Optional.ofNullable(t.opsForValue().get(key)).map(e -> JacksonUtils.readObjectValue(null, e, clazz)).orElse(null);
    }

    /**
     * 获得缓存的基本对象。
     *
     * @param template 如无特别需求，请设置为<code>null</code>
     * @param id       主键值 使用{@link RedisConstants#createCacheKey(Class, Serializable)}转换成Key
     * @return 缓存键值对应的数据
     */
    public static <T> T getObject(final StringRedisTemplate template, final Serializable id, final Class<T> clazz) {
        String key = RedisConstants.createCacheKey(clazz, id);
        return getObject(template, key, clazz);
    }


    /**
     * 获得缓存的基本对象。
     * <p>
     * 使用DCL技术避免缓存穿透
     *
     * @param template 如无特别需求，请设置为<code>null</code>
     * @param id       主键值 使用{@link RedisConstants#createCacheKey(Class, Serializable)}转换成Key
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
     * @param template 如无特别需求，请设置为<code>null</code>
     * @param keys
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> getObjects(final StringRedisTemplate template, final Class<T> clazz, final Collection<String> keys) {
        StringRedisTemplate t = Optional.ofNullable(template).orElseGet(() -> SpringUtils.getBean(StringRedisTemplate.class));
        List<String> valueString = t.opsForValue().multiGet(keys);
        return Optional.ofNullable(valueString).map(e -> EntityUtils.toList(e, f -> JacksonUtils.readObjectValue(null, f, clazz))).orElse(Collections.emptyList());
    }


    /**
     * 将JSON字符串转化成集合对象
     *
     * @param template 如无特别需求，请设置为<code>null</code>
     * @param ids
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> getObjects(final StringRedisTemplate template, final Collection<? extends Serializable> ids, final Class<T> clazz) {
        List<String> keys = EntityUtils.toList(ids, e -> RedisConstants.createCacheKey(clazz, e));
        return getObjects(template, clazz, keys);
    }


    /**
     * 将JSON字符串转化成集合对象
     *
     * @param template 如无特别需求，请设置为<code>null</code>
     * @param ids
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> List<T> getObjects(final StringRedisTemplate template, final Collection<Serializable> ids, long timeout, TimeUnit unit, final Class<T> clazz, Function<Collection<Serializable>, List<T>> action) {
        StringRedisTemplate tp = Optional.ofNullable(template).orElseGet(() -> SpringUtils.getBean(StringRedisTemplate.class));
        List<T> result = new ArrayList<>(ids.size());
        List<Serializable> remainIds = new ArrayList<>();
        for (Serializable id : ids) {
            // String key = RedisConstants.createCacheKey(clazz, id);
            T t = getObject(tp, id, clazz);
            if (t == null) {
                remainIds.add(id);
            }
            result.add(t);
        }

        if (remainIds.size() > 0) {
            for (Serializable id : remainIds) {
                T t = getObject(tp, id, clazz);
                if (t == null) {
                    synchronized (RedisConstants.createLockKey(clazz, id)) {
                        T t2 = ColUtils.toObj(action.apply(Collections.singletonList(id)));
                        if (t2 != null) {
                            save(tp, RedisConstants.createCacheKey(clazz, id), t2, timeout, unit);
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
     * @param template 如无特别需求，请设置为<code>null</code>
     * @param clazz
     * @param <T>
     * @return
     */
    public static <PO, T> List<T> getCollection(final StringRedisTemplate template, PO po, final Class<T> clazz) {
        StringRedisTemplate t = Optional.ofNullable(template).orElseGet(() -> SpringUtils.getBean(StringRedisTemplate.class));
        String poStr = JacksonUtils.writeValueAsString(null, po);
        String key = RedisConstants.createCacheKey(clazz, MD5Utils.md5(poStr));
        String jsonValue = t.opsForValue().get(key);
        return Optional.ofNullable(jsonValue).map(e -> JacksonUtils.readListValue(null, e, clazz)).orElse(Collections.emptyList());
    }


    /**
     * 通过PO封装查询条件，使用普通索引查询多条记录（结果集为集合）
     * <p>
     * 如果仅涉及主键查询，请使用{@link RedisUtils#getObject(StringRedisTemplate, Serializable, long, TimeUnit, Class, Function)}或者{@link RedisUtils#getObjects(StringRedisTemplate, Collection, long, TimeUnit, Class, Function)}
     * <p>
     * 使用JVM本地锁处理缓存
     *
     * @param template 如无特别需求，请设置为<code>null</code>
     * @param d        封装的查询对象
     * @param timeout  过期时间（小于或者等于0 表示缓存不过期）
     * @param unit     过期时间单位
     * @param clazz    与表一一对应的实体类CLass对象
     * @param action   访问数据库方法
     * @param <D>      封装查询条件类
     * @param <T>      与表一一对应的实体类
     * @return 集合
     */
    public static <D, T> List<T> getCollection(final StringRedisTemplate template, D d, long timeout, TimeUnit unit, final Class<T> clazz, Function<D, List<T>> action) {
        StringRedisTemplate t = Optional.ofNullable(template).orElseGet(() -> SpringUtils.getBean(StringRedisTemplate.class));
        String poStr = JacksonUtils.writeValueAsString(null, d);
        String key = RedisConstants.createCacheKey(clazz, MD5Utils.md5(poStr));

        List<T> tList = getCollection(t, d, clazz);
        if (tList == null || tList.isEmpty()) {
            synchronized (RedisConstants.createLockKey(clazz, MD5Utils.md5(poStr))) {
                List<T> tList2 = getCollection(template, d, clazz);
                if (tList2 == null || tList2.isEmpty()) {
                    save(t, key, action.apply(d), timeout, unit);
                    return getCollection(t, d, clazz);
                }
                return tList2;
            }
        }
        return tList;
    }

    /**
     * 删除单个对象
     *
     * @param template 如无特别需求，请设置为<code>null</code>
     * @param key
     */
    public static Boolean remove(final StringRedisTemplate template, final String key) {
        StringRedisTemplate t = Optional.ofNullable(template).orElseGet(() -> SpringUtils.getBean(StringRedisTemplate.class));
        return t.delete(key);
    }

    /**
     * 删除集合对象
     *
     * @param template 如无特别需求，请设置为<code>null</code>
     * @param keys     多个Key
     * @return
     */
    public static Long remove(final StringRedisTemplate template, final Collection<String> keys) {
        StringRedisTemplate t = Optional.ofNullable(template).orElseGet(() -> SpringUtils.getBean(StringRedisTemplate.class));
        return t.delete(keys);
    }

    /**
     * 批量保存
     *
     * @param template 如无特别需求，请设置为<code>null</code>
     * @param map
     */
    public static void multiSet(final StringRedisTemplate template, Map<String, String> map, long timeout, TimeUnit unit) {
        StringRedisTemplate t = Optional.ofNullable(template).orElseGet(() -> SpringUtils.getBean(StringRedisTemplate.class));
        t.opsForValue().multiSet(map);
    }


    /**
     * 批量保存
     *
     * @param template 如无特别需求，请设置为<code>null</code>
     * @param map
     * @param clazz
     * @param <T>
     */
    public static <T> void multiSet(final StringRedisTemplate template, Map<String, T> map, Class<T> clazz, long timeout, TimeUnit unit) {
        StringRedisTemplate t = Optional.ofNullable(template).orElseGet(() -> SpringUtils.getBean(StringRedisTemplate.class));
        Map<String, String> stringMap = MapUtils.transMap(map, e -> e, e -> JacksonUtils.writeValue(null, e));
        t.opsForValue().multiSet(stringMap);
    }

    /**
     * 批量查询Key对应的Value值
     *
     * @param template 如无特别需求，请设置为<code>null</code>
     * @param keys     批量Keys
     * @return Value值集合
     */
    public static List<String> multiGet(final StringRedisTemplate template, List<String> keys) {
        StringRedisTemplate t = Optional.ofNullable(template).orElseGet(() -> SpringUtils.getBean(StringRedisTemplate.class));
        return EntityUtils.toList(t.opsForValue().multiGet(keys), Function.identity(), Objects::nonNull);
    }

    /**
     * 批量查询Key对应的Value值
     *
     * @param template 如无特别需求，请设置为<code>null</code>
     * @param keys     批量Keys
     * @return Value值集合
     */
    public static <T> List<T> multiGet(final StringRedisTemplate template, List<String> keys, Class<T> clazz) {
        StringRedisTemplate t = Optional.ofNullable(template).orElseGet(() -> SpringUtils.getBean(StringRedisTemplate.class));
        return EntityUtils.toList(multiGet(t, keys), e -> JacksonUtils.readObjectValue(null, e, clazz), Objects::nonNull);
    }

    /**
     * 向channel发布消息
     * 如果消息是String类型，直接发送消息；
     * 如果不是，则转化序列化承JSON后发送消息
     *
     * @param template    如无特别需求，请设置为<code>null</code>
     * @param channelName channel名称
     * @param msg         消息
     * @since 1.4.5
     */
    public static <T> void publishMsg(final StringRedisTemplate template, final String channelName, T msg) {
        StringRedisTemplate t = Optional.ofNullable(template).orElseGet(() -> SpringUtils.getBean(StringRedisTemplate.class));
        if (msg instanceof String) {
            t.convertAndSend(channelName, msg);
        } else {
            /* 现将对象格式化为JSON字符串，然后保存 */
            String json = JacksonUtils.writeValueAsString(null, msg);
            Optional.ofNullable(json).ifPresent(e -> t.convertAndSend(channelName, e));
        }
    }
}

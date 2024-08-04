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
import org.redisson.api.RBatch;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;
import xin.altitude.cms.common.constant.RedisConstants;

import java.io.Serializable;
import java.time.Duration;
import java.util.*;
import java.util.function.Function;

/**
 * {@link RedissonUtils}工具类
 *
 * @author <a href="http://www.altitude.xin" target="_blank">赛泰先生</a>
 * @author <a href="https://gitee.com/decsa/ucode-cms-vue" target="_blank">UCode CMS</a>
 * @author <a href="https://space.bilibili.com/1936685014" target="_blank">赛泰先生的B站视频</a>
 * @since 1.6.5
 */
public abstract class RedissonUtils {
    private static final Logger log = LoggerFactory.getLogger(RedissonUtils.class);


    /**
     * <p>缓存基本的对象</p>
     *
     * @param client   如无特别需求，请设置为<code>null</code>
     * @param id       缓存的主键ID用以形成唯一key
     * @param value    缓存的值
     * @param duration 缓存是否过期：如果为<code>null</code>则表示不过期
     */
    public static <ID extends Serializable, T> void save(final RedissonClient client, final ID id, final T value, final Duration duration) {
        final String key;
        if (id instanceof String || id instanceof Long || id instanceof Integer) {
            key = RedisConstants.createCacheKey(value.getClass(), id);
        } else {
            throw new IllegalArgumentException("参数id类型不正确");
        }
        save(client, key, value, duration);
    }

    /**
     * <p>缓存基本的对象，Integer、String、实体类等 如果value是String类型，直接直接保存 如果不是，则转化序列化承JSON字符串后保存</p>
     * <p>支持指定过期时间</p>
     * <p>缓存对象值支持多种类型：普通单个实体类对象、{@link List}实体类集合、{@link IPage}分页对象、{@link Map}实例对象</p>
     *
     * @param client   如果为<code>null</code> 则自动从容器中获取
     * @param key      缓存的键值
     * @param value    缓存的值
     * @param duration 缓存是否过期：如果为<code>null</code>则表示不过期
     */
    public static void save(final RedissonClient client, final String key, final Object value, final Duration duration) {
        RedissonClient redissonClient = Optional.ofNullable(client).orElseGet(() -> SpringUtils.getBean(RedissonClient.class));

        String writeValue = JacksonUtils.writeValue(null, value);
        RBucket<String> bucket = redissonClient.getBucket(key);
        save(bucket, writeValue, duration);

    }

    /**
     * <p>缓存基本的对象，Integer、String、实体类等 如果value是String类型，直接直接保存 如果不是，则转化序列化承JSON字符串后保存</p>
     * <p>支持指定过期时间</p>
     * <p>缓存对象值支持多种类型：普通单个实体类对象、{@link List}实体类集合、{@link IPage}分页对象、{@link Map}实例对象</p>
     *
     * @param jsonValue 缓存的值
     * @param duration  缓存是否过期：如果为<code>null</code>则表示不过期
     */
    public static void save(RBucket<String> bucket, String jsonValue, Duration duration) {
        Assert.notNull(bucket, "bucket参数不能为空");
        Assert.notNull(jsonValue, "jsonValue参数不能为空");
        if (duration != null && !duration.isNegative()) {
            Optional.of(jsonValue).ifPresent(e -> bucket.set(e, duration));
            log.debug("object save successful,key:{},value:{}", bucket.getName(), jsonValue);
        } else {
            Optional.of(jsonValue).ifPresent(bucket::set);
            log.debug("object save successful,key:{},value:{},no expire time", bucket.getName(), jsonValue);
        }
    }

    /**
     * @param client 如无特别需求，请设置为<code>null</code>
     *               批量保存数据 设置过期时间
     */
    public static <V> void saveBatch(final RedissonClient client, final Map<String, V> map, final Duration duration) {
        RedissonClient t = Optional.ofNullable(client).orElseGet(() -> SpringUtils.getBean(RedissonClient.class));
        Map<String, String> data = MapUtils.transMap(map, e -> JacksonUtils.writeValue(null, e));
        RBatch batch = t.createBatch();

        // 遍历数据并添加到批量操作中
        if (duration != null && !duration.isNegative()) {
            data.forEach((key, value) -> batch.getBucket(key).setAsync(value, duration));
        } else {
            data.forEach((key, value) -> batch.getBucket(key).setAsync(value));
        }

        // 执行批量操作
        batch.execute();
    }

    /**
     * 设置有效时间
     *
     * @param client   如无特别需求，请设置为<code>null</code>
     * @param key      Redis键
     * @param duration 缓存是否过期：如果为<code>null</code>则表示不过期
     * @return true=设置成功；false=设置失败
     */
    public static boolean expire(final RedissonClient client, final String key, final Duration duration) {
        RedissonClient t = Optional.ofNullable(client).orElseGet(() -> SpringUtils.getBean(RedissonClient.class));
        return t.getBucket(key).expire(duration);
    }

    /**
     * 获得缓存的基本对象。
     *
     * @param client 如无特别需求，请设置为<code>null</code>
     * @param key    缓存键值
     * @return 缓存键值对应的数据
     */
    public static <T> T getObject(final RedissonClient client, final String key, final Class<T> clazz) {
        RedissonClient t = Optional.ofNullable(client).orElseGet(() -> SpringUtils.getBean(RedissonClient.class));

        RBucket<String> bucket = t.getBucket(key);
        return getObject(bucket, clazz);
    }

    public static <T> T getObject(RBucket<String> bucket, Class<T> clazz) {
        return Optional.ofNullable(bucket.get()).map(e -> JacksonUtils.readObjectValue(null, e, clazz)).orElse(null);
    }

    /**
     * 获得缓存的基本对象。
     *
     * @param client 如无特别需求，请设置为<code>null</code>
     * @param id     主键值 使用{@link RedisConstants#createCacheKey(Class, Serializable)}转换成Key
     * @return 缓存键值对应的数据
     */
    public static <T> T getObject(final RedissonClient client, final Serializable id, final Class<T> clazz) {
        if (id instanceof String || id instanceof Long || id instanceof Integer) {
            String key = RedisConstants.createCacheKey(clazz, id);
            return getObject(client, key, clazz);
        } else {
            throw new IllegalArgumentException("参数id类型不正确");
        }

    }


    /**
     * 获得缓存的基本对象。
     * <p>
     * 使用DCL技术避免缓存穿透
     *
     * @param client 如无特别需求，请设置为<code>null</code>
     * @param id     主键值 使用{@link RedisConstants#createCacheKey(Class, Serializable)}转换成Key
     * @return 缓存键值对应的数据
     */
    public static <T> T getObject(final RedissonClient client, final Serializable id, final Duration duration, final Class<T> clazz, Function<Serializable, T> action) {
        String key = RedisConstants.createCacheKey(clazz, id);
        T t = getObject(client, key, clazz);
        if (t == null) {
            synchronized (RedisConstants.createLockKey(clazz, id)) {
                T t2 = getObject(client, key, clazz);
                if (t2 == null) {
                    save(client, key, action.apply(id), duration);
                    return getObject(client, key, clazz);
                }
                return t2;
            }
        } else {
            return t;
        }
    }

}

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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.BoundSetOperations;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author explore
 * @since 1.4
 */
public class RedisUtils {

    private static final StringRedisTemplate STRING_REDIS_TEMPLATE = SpringUtils.getBean(StringRedisTemplate.class);
    private static final ValueOperations<String, String> OPS_FOR_VALUE = STRING_REDIS_TEMPLATE.opsForValue();


    /*********************************************************************************
     *
     * 对bitmap的操作
     *
     ********************************************************************************/

    /**
     * 将指定offset的值设置为1
     *
     * @param key    bitmap结构的key
     * @param offset 要设置偏移的key
     * @return 返回设置该value之前的值。
     */
    public static Boolean setBit(String key, int offset) {
        validOffset(offset);
        return setBit(key, offset, true);
    }

    /**
     * 将指定offset的值设置为1
     *
     * @param key    bitmap结构的key
     * @param offset 要设置偏移的key
     * @param value  true：即该位设置为1，否则设置为0
     * @return 返回设置该value之前的值。
     */
    public static Boolean setBit(String key, int offset, Boolean value) {
        validOffset(offset);
        return OPS_FOR_VALUE.setBit(key, offset, value);
    }

    /**
     * 将指定offset的值设置为0
     *
     * @param key    bitmap结构的key
     * @param offset 要移除偏移的key
     * @return 若偏移位上的值为1，那么返回true。
     */
    public static Boolean getBit(String key, int offset) {
        validOffset(offset);
        return OPS_FOR_VALUE.getBit(key, offset);
    }

    /**
     * 将指定offset偏移量的值设置为1；
     *
     * @param key    bitmap结构的key
     * @param offset 指定的偏移量。
     * @return 返回设置该value之前的值。
     */
    public static Boolean setBit(String key, Long offset) {
        validOffset(offset);
        return setBit(key, offset, true);
    }

    /**
     * 将指定offset偏移量的值设置为1；
     *
     * @param key    bitmap结构的key
     * @param offset 指定的偏移量。
     * @param value  true：即该位设置为1，否则设置为0
     * @return 返回设置该value之前的值。
     */
    public static Boolean setBit(String key, Long offset, Boolean value) {
        validOffset(offset);
        return OPS_FOR_VALUE.setBit(key, offset, value);
    }

    /**
     * 将指定offset偏移量的值设置为0；
     *
     * @param key    bitmap结构的key
     * @param offset 指定的偏移量。
     * @return 若偏移位上的值为1，那么返回true。
     */
    public static Boolean getBit(String key, Long offset) {
        validOffset(offset);
        return OPS_FOR_VALUE.getBit(key, offset);
    }

    /**
     * 统计对应的bitmap上value为1的数量
     *
     * @param key bitmap的key
     * @return value等于1的数量
     */
    public static Long bitCount(String key) {
        return STRING_REDIS_TEMPLATE.execute((RedisCallback<Long>) con -> con.bitCount(key.getBytes()));
    }

    /**
     * 统计指定范围中value为1的数量
     *
     * @param key   bitMap中的key
     * @param start 该参数的单位是byte（1byte=8bit），{@code setBit(key,7,true);}进行存储时，单位是bit。
     *              那么只需要统计[0,1]便可以统计到上述set的值。
     * @param end   该参数的单位是byte。
     * @return 在指定范围[start*8,end*8]内所有value=1的数量
     */
    public static Long bitCount(String key, int start, int end) {
        return STRING_REDIS_TEMPLATE.execute((RedisCallback<Long>) con -> con.bitCount(key.getBytes(), start, end));
    }

    /**
     * 对一个或多个保存二进制的字符串key进行元操作，并将结果保存到saveKey上。
     * <p>
     * bitop and saveKey key [key...]，对一个或多个key逻辑并，结果保存到saveKey。
     * bitop or saveKey key [key...]，对一个或多个key逻辑或，结果保存到saveKey。
     * bitop xor saveKey key [key...]，对一个或多个key逻辑异或，结果保存到saveKey。
     * bitop xor saveKey key，对一个或多个key逻辑非，结果保存到saveKey。
     * <p>
     *
     * @param op      元操作类型；
     * @param saveKey 元操作后将结果保存到saveKey所在的结构中。
     * @param desKey  需要进行元操作的类型。
     * @return 1：返回元操作值。
     */
    public static Long bitOp(RedisStringCommands.BitOperation op, String saveKey, String... desKey) {
        byte[][] bytes = new byte[desKey.length][];
        for (int i = 0; i < desKey.length; i++) {
            bytes[i] = desKey[i].getBytes();
        }
        return STRING_REDIS_TEMPLATE.execute((RedisCallback<Long>) con -> con.bitOp(op, saveKey.getBytes(), bytes));
    }

    /**
     * 对一个或多个保存二进制的字符串key进行元操作，并将结果保存到saveKey上，并返回统计之后的结果。
     *
     * @param op      元操作类型；
     * @param saveKey 元操作后将结果保存到saveKey所在的结构中。
     * @param desKey  需要进行元操作的类型。
     * @return 返回saveKey结构上value=1的所有数量值。
     */
    public static Long bitOpResult(RedisStringCommands.BitOperation op, String saveKey, String... desKey) {
        bitOp(op, saveKey, desKey);
        return bitCount(saveKey);
    }

    /**
     * 验证offset是否合法
     * Redis字符串支持字符串最大长度512M，因此支持offset的最大值为(2^32)-1
     *
     * @param offset 偏移量
     */
    private static void validOffset(long offset) {
        Assert.isTrue(offset >= 0 && offset <= 4294967295L, "参数【offset】越界");
    }

    /**
     * ///////////////////////////////////////////////////////////////
     * 处理字符串缓存
     * ///////////////////////////////////////////////////////////////
     */

    /**
     * 获取多个Hash中的数据
     *
     * @param key   Redis键
     * @param hKeys Hash键集合
     * @return Hash对象集合
     */
    public static List<Object> getMultiCacheMapValue(final String key, final Collection<Object> hKeys) {
        return STRING_REDIS_TEMPLATE.opsForHash().multiGet(key, hKeys);
    }

    /**
     * 获得缓存的list对象
     *
     * @param key 缓存的键值
     * @return 缓存键值对应的数据
     */
    public static List<String> getCacheList(final String key) {
        return STRING_REDIS_TEMPLATE.opsForList().range(key, 0, -1);
    }

    /**
     * 获得缓存的set
     *
     * @param key
     * @return
     */
    public static Set<String> getCacheSet(final String key) {
        return STRING_REDIS_TEMPLATE.opsForSet().members(key);
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     * 如果value是String类型，直接直接保存
     * 如果不是，则转化序列化承JSON后保存
     *
     * @param key   缓存的键值
     * @param value 缓存的值
     */
    public static void setObject(final String key, final Object value) {
        if (value instanceof String) {
            OPS_FOR_VALUE.set(key, (String) value);
        } else {
            try {
                /* 现将对象格式化为JSON字符串，然后保存 */
                String json = SpringUtils.getBean(ObjectMapper.class).writeValueAsString(value);
                Optional.ofNullable(json).ifPresent(e -> OPS_FOR_VALUE.set(key, e));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 缓存基本的对象，Integer、String、实体类等
     * 如果value是String类型，直接直接保存
     * 如果不是，则转化序列化承JSON后保存
     *
     * @param key      缓存的键值
     * @param value    缓存的值
     * @param timeout  时间
     * @param timeUnit 时间颗粒度
     */
    public static void setObject(final String key, final Object value, final Integer timeout, final TimeUnit timeUnit) {
        if (value instanceof String) {
            OPS_FOR_VALUE.set(key, (String) value, timeout, timeUnit);
        } else {
            try {
                /* 现将对象格式化为JSON字符串，然后保存 */
                String json = SpringUtils.getBean(ObjectMapper.class).writeValueAsString(value);
                Optional.ofNullable(json).ifPresent(e -> OPS_FOR_VALUE.set(key, e, timeout, timeUnit));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 缓存基本的对象，Integer、String、实体类等
     * 如果value是String类型，直接直接保存
     * 如果不是，则转化序列化承JSON后保存
     *
     * @param key   缓存的键值
     * @param value 缓存的值
     * @return 如果保存成功，则返回true
     */
    public static Boolean setObjectIfAbsent(final String key, final Object value) {
        if (value instanceof String) {
            return OPS_FOR_VALUE.setIfAbsent(key, (String) value);
        } else {
            try {
                /* 现将对象格式化为JSON字符串，然后保存 */
                String json = SpringUtils.getBean(ObjectMapper.class).writeValueAsString(value);
                return Optional.ofNullable(json).map(e -> OPS_FOR_VALUE.setIfAbsent(key, e)).orElse(false);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 缓存基本的对象，Integer、String、实体类等
     * 如果value是String类型，直接直接保存
     * 如果不是，则转化序列化承JSON后保存
     *
     * @param key      缓存的键值
     * @param value    缓存的值
     * @param timeout  时间
     * @param timeUnit 时间颗粒度
     * @return 如果保存成功，则返回true
     */
    public static Boolean setObjectIfAbsent(final String key, final Object value, final Integer timeout, final TimeUnit timeUnit) {
        if (value instanceof String) {
            return OPS_FOR_VALUE.setIfAbsent(key, (String) value, timeout, timeUnit);
        } else {
            try {
                /* 现将对象格式化为JSON字符串，然后保存 */
                String json = SpringUtils.getBean(ObjectMapper.class).writeValueAsString(value);
                return Optional.ofNullable(json).map(e -> OPS_FOR_VALUE.setIfAbsent(key, e, timeout, timeUnit)).orElse(false);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    /**
     * 设置有效时间
     *
     * @param key     Redis键
     * @param timeout 超时时间
     * @return true=设置成功；false=设置失败
     */
    public static Boolean expire(final String key, final Long timeout) {
        return expire(key, timeout, TimeUnit.SECONDS);
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
        return STRING_REDIS_TEMPLATE.expire(key, timeout, unit);
    }

    /**
     * 获得缓存的基本对象。
     *
     * @param key 缓存键值
     * @return 缓存键值对应的数据
     */
    public static <T> T getObject(final String key, Class<T> clazz) {
        String fromValue = OPS_FOR_VALUE.get(key);
        if (fromValue != null) {
            try {
                return SpringUtils.getBean(ObjectMapper.class).readValue(fromValue, clazz);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 删除单个对象
     *
     * @param key
     */
    public static Boolean deleteObject(final String key) {
        return STRING_REDIS_TEMPLATE.delete(key);
    }

    /**
     * 删除集合对象
     *
     * @param keys 多个Key
     * @return
     */
    public static Long deleteObject(final Collection<String> keys) {
        return STRING_REDIS_TEMPLATE.delete(keys);
    }

    /**
     * 缓存List数据
     *
     * @param key      缓存的键值
     * @param dataList 待缓存的List数据
     * @return 缓存的对象
     */
    public static Long setCacheList(final String key, final List<String> dataList) {
        Long count = STRING_REDIS_TEMPLATE.opsForList().rightPushAll(key, dataList);
        return count == null ? 0 : count;
    }

    /**
     * 缓存Set
     *
     * @param key     缓存键值
     * @param dataSet 缓存的数据
     * @return 缓存数据的对象
     */
    public static BoundSetOperations<String, String> setCacheSet(final String key, final Set<String> dataSet) {
        BoundSetOperations<String, String> setOperation = STRING_REDIS_TEMPLATE.boundSetOps(key);
        for (String s : dataSet) {
            setOperation.add(s);
        }
        return setOperation;
    }

    /**
     * 缓存Map
     *
     * @param key
     * @param dataMap
     */
    public static void setCacheMap(final String key, final Map<String, String> dataMap) {
        if (dataMap != null) {
            STRING_REDIS_TEMPLATE.opsForHash().putAll(key, dataMap);
        }
    }

    /**
     * 获得缓存的Map
     *
     * @param key
     * @return
     */
    public static Map<Object, Object> getCacheMap(final String key) {
        return STRING_REDIS_TEMPLATE.opsForHash().entries(key);
    }

    /**
     * 往Hash中存入数据
     *
     * @param key   Redis键
     * @param hKey  Hash键
     * @param value 值
     */
    public static void setCacheMapValue(final String key, final String hKey, final String value) {
        STRING_REDIS_TEMPLATE.opsForHash().put(key, hKey, value);
    }

    /**
     * 获取Hash中的数据
     *
     * @param key  Redis键
     * @param hKey Hash键
     * @return Hash中的对象
     */
    public static String getCacheMapValue(final String key, final String hKey) {
        HashOperations<String, String, String> opsForHash = STRING_REDIS_TEMPLATE.opsForHash();
        return opsForHash.get(key, hKey);
    }

    /**
     * 删除Hash中的数据
     *
     * @param key
     * @param hkey
     */
    public static void delCacheMapValue(final String key, final String hkey) {
        HashOperations<String,String,Object> hashOperations = STRING_REDIS_TEMPLATE.opsForHash();
        hashOperations.delete(key, hkey);
    }

    /**
     * 获得缓存的基本对象列表
     *
     * @param pattern 字符串前缀
     * @return 对象列表
     */
    public static Collection<String> keys(final String pattern) {
        return STRING_REDIS_TEMPLATE.keys(pattern);
    }

    //***** 发布消息

    /**
     * 向channel发布消息
     * 如果消息是String类型，直接发送消息；
     * 如果不是，则转化序列化承JSON后发送消息
     *
     * @param channelName channel名称
     * @param msg         消息
     * @since 1.4.5
     */
    public static void publishMsg(final String channelName, Object msg) {
        if (msg instanceof String) {
            STRING_REDIS_TEMPLATE.convertAndSend(channelName, msg);
        } else {
            try {
                /* 现将对象格式化为JSON字符串，然后保存 */
                String json = SpringUtils.getBean(ObjectMapper.class).writeValueAsString(msg);
                Optional.ofNullable(json).ifPresent(e -> STRING_REDIS_TEMPLATE.convertAndSend(channelName, e));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }
}

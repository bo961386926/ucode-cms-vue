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

import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.Assert;

/**
 * @author explore
 * @since 1.4
 */
public class RedisUtils {

    private static final StringRedisTemplate STRING_REDIS_TEMPLATE = SpringUtils.getBean(StringRedisTemplate.class);


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
    public static Boolean setBit(String key, int offset, boolean value) {
        validOffset(offset);
        return STRING_REDIS_TEMPLATE.opsForValue().setBit(key, offset, value);
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
        return STRING_REDIS_TEMPLATE.opsForValue().getBit(key, offset);
    }

    /**
     * 将指定offset偏移量的值设置为1；
     *
     * @param key    bitmap结构的key
     * @param offset 指定的偏移量。
     * @return 返回设置该value之前的值。
     */
    public static Boolean setBit(String key, long offset) {
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
    public static Boolean setBit(String key, long offset, boolean value) {
        validOffset(offset);
        return STRING_REDIS_TEMPLATE.opsForValue().setBit(key, offset, value);
    }

    /**
     * 将指定offset偏移量的值设置为0；
     *
     * @param key    bitmap结构的key
     * @param offset 指定的偏移量。
     * @return 若偏移位上的值为1，那么返回true。
     */
    public static Boolean getBit(String key, long offset) {
        validOffset(offset);
        return STRING_REDIS_TEMPLATE.opsForValue().getBit(key, offset);
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

}

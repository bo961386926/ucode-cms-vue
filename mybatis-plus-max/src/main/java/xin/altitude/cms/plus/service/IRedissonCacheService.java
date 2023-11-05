/*
 *
 * Copyright (c) 2020-2023, 赛泰先生 (http://www.altitude.xin).
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

package xin.altitude.cms.plus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import xin.altitude.cms.plus.util.PlusUtils;

import java.io.Serializable;

/**
 * 增强{@link IService} 用于实现Redis分布式缓存功能
 *
 * @author <a href="http://www.altitude.xin" target="_blank">赛泰先生</a>
 * @author <a href="https://gitee.com/decsa/ucode-cms-vue" target="_blank">UCode CMS</a>
 * @author <a href="https://space.bilibili.com/1936685014" target="_blank">B站视频</a>
 **/
public interface IRedissonCacheService<T> extends ICacheService<T> {


    /**
     * <p>重载{@link IService#getById(Serializable)}方法 增加Redis分布式缓存功能</p>
     * <p>增加分布式锁的支持 防止高并发场景下<i>缓存穿透</i></p>
     *
     * @param id       主键ID
     * @param ms       过期时间 毫秒
     * @param redisson {@link Redisson}实例
     * @return {@code T}实体类对象实例
     */
    default T getById(Serializable id, long ms, RedissonClient redisson) {
        return PlusUtils.ifNotCache(id, getEntityClass(), ms, this::getById, redisson);
    }
}

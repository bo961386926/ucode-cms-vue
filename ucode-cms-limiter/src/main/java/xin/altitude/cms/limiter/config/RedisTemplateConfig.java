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

package xin.altitude.cms.limiter.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;

/**
 * @author explore
 * @since 2021/03/19 23:37
 **/
public class RedisTemplateConfig {
    /**
     * 向容器注入LimitRedisTemplate
     *
     * @param factory
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(LimitRedisTemplate.class)
    public LimitRedisTemplate limitRedisTemplate(RedisConnectionFactory factory) {
        LimitRedisTemplate template = new LimitRedisTemplate();
        template.setConnectionFactory(factory);
        return template;
    }
}

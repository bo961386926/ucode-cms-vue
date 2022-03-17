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

package xin.altitude.cms.limiter.aspectj;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.util.ColUtils;
import xin.altitude.cms.common.util.ServletUtils;
import xin.altitude.cms.limiter.annotation.RateLimiter;
import xin.altitude.cms.limiter.config.RedisScriptConfig;
import xin.altitude.cms.limiter.enums.LimitType;
import xin.altitude.cms.limiter.exception.ServiceException;
import xin.altitude.cms.limiter.util.IpUtils;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * 限流处理
 *
 * @author ucode
 */
@Aspect
@Import({RedisScriptConfig.class})
public class RateLimiterAspect {
    private static final Logger log = LoggerFactory.getLogger(RateLimiterAspect.class);

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private RedisScript<Long> redisScript;

    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    public void init() {
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
    }

    @Before("@annotation(rateLimiter)")
    public void doBefore(JoinPoint point, RateLimiter rateLimiter) throws JsonProcessingException {
        String key = rateLimiter.key();

        int count = rateLimiter.count();
        int time = rateLimiter.time();

        List<String> keys = ColUtils.toCol(getCombineKey(rateLimiter, point));
        List<String> args = Arrays.asList(String.valueOf(count), String.valueOf(time));
        Long result = redisTemplate.execute(redisScript, keys, args.toArray());
        /* 服务被限流 */
        if (result != null && result == 1L) {
            String msg = String.format("访问过于频繁，请稍候再试，当前请求允许访问速率为{%d次/%d秒}", count, time);
            log.info(msg);
            throw new ServiceException(msg);
        }

    }

    public String getCombineKey(RateLimiter rateLimiter, JoinPoint point) {
        StringBuilder sb = new StringBuilder(rateLimiter.key());
        if (rateLimiter.limitType() == LimitType.IP) {
            sb.append(IpUtils.getIpAddr(ServletUtils.getRequest())).append("-");
        }
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();
        Class<?> targetClass = method.getDeclaringClass();
        sb.append(targetClass.getName()).append("-").append(method.getName());
        return sb.toString();
    }

}

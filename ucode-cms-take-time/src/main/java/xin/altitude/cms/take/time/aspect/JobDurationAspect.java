/*
 * Copyright (c) 2021. 北京流深数据科技有限公司
 */

package xin.altitude.cms.take.time.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.data.redis.core.StringRedisTemplate;
import xin.altitude.cms.common.util.SpringUtils;
import xin.altitude.cms.take.time.annotation.JobDuration;

import java.util.Objects;


/**
 * 耗时统计，借助AOP统一处理切片逻辑
 */
@Aspect
@Lazy
public class JobDurationAspect {

    public static final String KEY = "job-duration";
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 带有@TakeTime注解的方法
     */
    @Pointcut("@annotation(xin.altitude.cms.take.time.annotation.JobDuration)"
        + "|| @within(xin.altitude.cms.take.time.annotation.JobDuration)")
    public void pointCut() {

    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long start = System.currentTimeMillis();
        JobDuration annotation = getAnnotation(point);

        logger.debug("job start ↓↓↓↓↓");
        Object result = point.proceed();

        // 如果配置需要持久化，则保存到数据库
        if (annotation != null) {
            // 使用Redis来落库
            long end = System.currentTimeMillis();
            long duration = end - start;


            StringRedisTemplate stringRedisTemplate = getRedisTemplate(annotation);
            String value = getValue(point, annotation);
            Double score = stringRedisTemplate.opsForZSet().score(KEY, value);
            // 平均响应时间
            if (score != null) {
                double avgDuration = (score + duration) / 2;
                stringRedisTemplate.opsForZSet().add(KEY, value, avgDuration);
            } else {
                stringRedisTemplate.opsForZSet().add(KEY, value, duration);
            }
            logger.debug("key:{} value:{} score:{}", KEY, value, duration);
        }
        logger.debug("job end   ↑↑↑↑↑");
        return result;
    }

    private StringRedisTemplate getRedisTemplate(JobDuration annotation) {
        String redisTemplate = annotation.redisTemplate();
        if ("".equals(redisTemplate)) {
            return SpringUtils.getBean(StringRedisTemplate.class);
        } else {
            return SpringUtils.getBean(redisTemplate);
        }
    }

    private String getValue(ProceedingJoinPoint point, JobDuration annotation) {
        String targetClassName = point.getTarget().getClass().getName();
        String targetMethodName = point.getSignature().getName();
        if ("".equals(annotation.jobName())) {
            return targetClassName + ":" + targetMethodName;
        } else {
            return annotation.jobName();
        }

    }


    /**
     * 查询注解
     */
    private JobDuration getAnnotation(ProceedingJoinPoint point) {
        MethodSignature signature = (MethodSignature) point.getSignature();
        JobDuration duration = AnnotationUtils.findAnnotation(signature.getMethod(), JobDuration.class);
        if (Objects.nonNull(duration)) {
            return duration;
        }

        return AnnotationUtils.findAnnotation(signature.getDeclaringType(), JobDuration.class);
    }
}

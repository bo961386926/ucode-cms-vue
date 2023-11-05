/*
 * Copyright (c) 2021. 北京流深数据科技有限公司
 */

package xin.altitude.cms.take.time.annotation;

import xin.altitude.cms.take.time.aspect.JobDurationAspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 异步任务 方法执行 耗时统计
 * <p>
 * 支持标记Method
 *
 * @author xiao_lei
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface JobDuration {
    /**
     * 支持自定义 redisTemplate
     * <p>
     * 如果不指定 则从容器中获取默认 redisTemplate
     * <p>
     * 非必须字段
     */
    String redisTemplate() default "";


    /**
     * Redis Key值
     * <p>
     * 如果有不同类别的任务
     * <p>
     * 非必须字段
     */
    String redisKey() default JobDurationAspect.KEY;

    /**
     * 任务名称
     * <p>
     * 默认使用方法所在的类名+方法名来作为任务名称
     * <p>
     * 可自定义 需要确保唯一性
     * <p>
     * 非必须字段
     */
    String jobName() default "";
}

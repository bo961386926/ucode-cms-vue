/*
 * Copyright (c) 2021. 北京流深数据科技有限公司
 */

package xin.altitude.cms.take.time.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import xin.altitude.cms.common.entity.AjaxResult;


/**
 * 耗时统计，借助AOP统一处理切片逻辑
 *
 * @author explore
 */
@Aspect
public class TakeTimeAspect {
    /**
     * 统计请求的处理时间
     */
    ThreadLocal<Long> startTime = new ThreadLocal<>();

    /**
     * 带有@TakeTime注解的方法
     */
    @Pointcut("@annotation(xin.altitude.cms.take.time.annotation.TakeTime)")
    public void time() {

    }

    @Before("time()")
    public void doBefore(JoinPoint joinPoint) {
        startTime.set(System.currentTimeMillis());
    }

    @AfterReturning(returning = "ajaxResult", pointcut = "time()")
    public void doAfterReturning(AjaxResult ajaxResult) {
        long l = System.currentTimeMillis() - startTime.get();
        ajaxResult.put("time", String.format("API接口耗时【%d】毫秒", l));
        startTime.remove();
    }
}

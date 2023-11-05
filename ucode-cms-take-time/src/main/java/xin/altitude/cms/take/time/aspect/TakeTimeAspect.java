/*
 * Copyright (c) 2021. 北京流深数据科技有限公司
 */

package xin.altitude.cms.take.time.aspect;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import xin.altitude.cms.take.time.annotation.TakeTime;
import xin.altitude.cms.take.time.uril.DurationUtils;

import java.util.Map;


/**
 * 耗时统计，借助AOP统一处理切片逻辑
 *
 * @author <a href="http://www.altitude.xin" target="_blank">赛泰先生</a>
 * @author <a href="https://gitee.com/decsa/ucode-cms-vue" target="_blank">UCode CMS</a>
 * @author <a href="https://space.bilibili.com/1936685014" target="_blank">B站视频</a>
 */
@Aspect
public class TakeTimeAspect {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 带有@TakeTime注解的方法
     */
    @Pointcut("@annotation(xin.altitude.cms.take.time.annotation.TakeTime)")
    public void pointCut() {

    }

    @Around("pointCut()")
    @SuppressWarnings("unchecked")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long start = System.currentTimeMillis();
        Object result = point.proceed();
        // 如果返回值是Map，则添加接口耗时字段
        if (result instanceof Map) {
            Map<Object, Object> map = (Map<Object, Object>) result;
            long l = System.currentTimeMillis() - start;
            String duration = DurationUtils.calTime(l);
            map.put("duration", duration);
            logger.debug("{} annotation marked this method exec duration: {}", TakeTime.class.getSimpleName(), duration);
            return map;
        }
        return result;

    }
}

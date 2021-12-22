package xin.altitude.cms.common.annotation;

import xin.altitude.cms.common.constant.Constants;
import xin.altitude.cms.common.enums.LimitType;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 限流注解
 *
 * @author ucode
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimiter {
    /**
     * 限流key
     */
    public String key() default Constants.RATE_LIMIT_KEY;
    
    /**
     * 限流时间,单位秒
     */
    public int time() default 60;
    
    /**
     * 限流次数
     */
    public int count() default 100;
    
    /**
     * 限流类型
     */
    public LimitType limitType() default LimitType.DEFAULT;
}

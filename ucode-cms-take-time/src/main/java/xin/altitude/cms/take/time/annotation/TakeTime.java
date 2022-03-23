/*
 * Copyright (c) 2021. 北京流深数据科技有限公司
 */

package xin.altitude.cms.take.time.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 统计耗时
 *
 * @author explore
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TakeTime {
}

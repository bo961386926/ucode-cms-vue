package xin.altitude.demo.jpa.ddl.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注释标记注解
 *
 * @author 赛泰先生
 */
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
public @interface Comment {
    /**
     * 注释的值
     *
     * @return 字段注释
     */
    String value() default "";
}

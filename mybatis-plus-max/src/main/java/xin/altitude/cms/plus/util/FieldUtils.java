package xin.altitude.cms.plus.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * @author <a href="http://www.altitude.xin" target="_blank">Java知识图谱</a>
 * @author <a href="https://gitee.com/decsa/ucode-cms-vue" target="_blank">UCode CMS</a>
 * @author <a href="https://space.bilibili.com/1936685014" target="_blank">B站视频</a>
 **/
public class FieldUtils {
    private final static Logger LOGGER = LoggerFactory.getLogger(FieldUtils.class);

    private FieldUtils() {
    }


    /**
     * 获取实体类中带有标记注解的属性值
     *
     * @param entity
     * @param annotationClass
     * @param <T>
     * @param <S>
     * @return
     */
    public static <T, S extends Annotation> Serializable getFieldValue(T entity, Class<S> annotationClass) {
        Field[] declaredFields = entity.getClass().getDeclaredFields();
        for (Field declaredField : declaredFields) {
            S pkVal = declaredField.getAnnotation(annotationClass);
            if (pkVal != null) {
                declaredField.setAccessible(true);
                try {
                    return (Serializable) declaredField.get(entity);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        LOGGER.error("实体类: {}需要添加注解: {}", entity, annotationClass);
        throw new RuntimeException(String.format("实体类: %s需要添加注解: %s", entity, annotationClass));
    }
}

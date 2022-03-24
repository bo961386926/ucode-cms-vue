package xin.altitude.cms.common.util;

import xin.altitude.cms.common.lang.AbstractObject;

/**
 * Clone工具类
 *
 * @author explore
 * @since 2022/03/24 19:49
 **/
public class CloneUtils {
    /**
     * 使用原生clone方法复制对象
     *
     * @param obj 原始对象实例
     * @param <T> 原始对象泛型
     * @return 原始对象的深拷贝
     */
    @SuppressWarnings("unchecked")
    public static <T extends AbstractObject> T clone(T obj) {
        if (obj != null) {
            try {
                return (T) obj.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

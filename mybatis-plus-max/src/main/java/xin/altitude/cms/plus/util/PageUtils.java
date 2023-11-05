package xin.altitude.cms.plus.util;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 * @author <a href="http://www.altitude.xin" target="_blank">Java知识图谱</a>
 * @author <a href="https://gitee.com/decsa/ucode-cms-vue" target="_blank">UCode CMS</a>
 * @author <a href="https://space.bilibili.com/1936685014" target="_blank">B站视频</a>
 **/
public class PageUtils {
    private PageUtils() {
    }


    /**
     * 判断{@link IPage}对象实例是否为空
     *
     * @param page 分页对象实例
     * @param <T>  元素类型泛型
     * @return true/false
     */
    public static <T> boolean isEmpty(IPage<T> page) {
        if (page != null) {
            List<T> records = page.getRecords();
            return records != null && records.isEmpty();
        }
        return true;
    }

    /**
     * 判断{@link IPage}对象实例是否不为空
     */
    public static <T> boolean isNotEmpty(IPage<T> page) {
        return !isEmpty(page);
    }
}

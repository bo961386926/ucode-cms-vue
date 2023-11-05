package xin.altitude.cms.plus.lang;

import java.util.List;

/**
 * MybatisPlus多表连接查询指定外键值
 *
 * @author <a href="http://www.altitude.xin" target="_blank">赛泰先生</a>
 * @author <a href="https://gitee.com/decsa/ucode-cms-vue" target="_blank">UCode CMS</a>
 * @author <a href="https://space.bilibili.com/1936685014" target="_blank">B站视频</a>
 **/
public interface PkFieldValue<T, S> extends PkField<T> {

    /**
     * 给列表赋值
     *
     * @param list 列表
     */
    void setList(List<S> list);
}

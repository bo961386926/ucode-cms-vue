package xin.altitude.cms.plus.lang;

/**
 * 指定主键字段
 *
 * @author <a href="http://www.altitude.xin" target="_blank">Java知识图谱</a>
 * @author <a href="https://gitee.com/decsa/ucode-cms-vue" target="_blank">UCode CMS</a>
 * @author <a href="https://space.bilibili.com/1936685014" target="_blank">B站视频</a>
 * @since 1.6.4
 **/
public interface PkField<T> {
    /**
     * 指定当前实体类的主键列的值
     *
     * @return 主键值
     */
    T pkVal();

}

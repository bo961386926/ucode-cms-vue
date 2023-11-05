package xin.altitude.cms.plus.lang;

/**
 * @author <a href="http://www.altitude.xin" target="_blank">Java知识图谱</a>
 * @author <a href="https://gitee.com/decsa/ucode-cms-vue" target="_blank">UCode CMS</a>
 * @author <a href="https://space.bilibili.com/1936685014" target="_blank">B站视频</a>
 **/
public interface IdValue<K, V> {
    /**
     * 指定当前实体类的主键列的值
     *
     * @return 主键值
     */
    K pkVal();

    /**
     * 给指定字段赋值
     *
     * @param value 值
     */
    void setValue(V value);
}

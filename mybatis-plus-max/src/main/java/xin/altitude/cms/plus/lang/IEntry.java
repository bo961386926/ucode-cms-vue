package xin.altitude.cms.plus.lang;

/**
 * @author <a href="http://www.altitude.xin" target="_blank">Java知识图谱</a>
 * @author <a href="https://gitee.com/decsa/ucode-cms-vue" target="_blank">UCode CMS</a>
 * @author <a href="https://space.bilibili.com/1936685014" target="_blank">B站视频</a>
 **/
public interface IEntry<K, V> {
    /**
     * 获取Key的值
     *
     * @return K实例
     */
    K getKey();

    /**
     * 获取Value的值
     *
     * @return V实例
     */
    V getValue();
}

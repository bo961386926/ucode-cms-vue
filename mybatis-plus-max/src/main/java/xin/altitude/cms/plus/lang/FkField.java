package xin.altitude.cms.plus.lang;

/**
 * MybatisPlus多表连接查询指定外键值
 *
 * @author <a href="http://www.altitude.xin" target="_blank">赛泰先生</a>
 * @author <a href="https://gitee.com/decsa/ucode-cms-vue" target="_blank">UCode CMS</a>
 * @author <a href="https://space.bilibili.com/1936685014" target="_blank">B站视频</a>
 **/
public interface FkField<T> {
    /**
     * 获取外键值
     *
     * @return 外键值
     */
    T fkVal();
}

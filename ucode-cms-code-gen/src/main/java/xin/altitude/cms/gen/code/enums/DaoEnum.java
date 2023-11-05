package xin.altitude.cms.gen.code.enums;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 数据库访问层枚举
 *
 * @author <a href="http://www.altitude.xin" target="_blank">赛泰先生</a>
 * @author <a href="https://gitee.com/decsa/ucode-cms-vue" target="_blank">UCode CMS</a>
 * @author <a href="https://space.bilibili.com/1936685014" target="_blank">B站视频</a>
 **/
public enum DaoEnum {
    /**
     * DO实体类
     */
    mybatis("mybatis", "mybatis"),
    /**
     * MybatisPlus
     */
    mybatisPlus("mybatisPlus", "MybatisPlus");
    
    private final String value;
    private final String desc;
    
    DaoEnum(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }
    
    public static Set<DaoEnum> all() {
        return new HashSet<>(Arrays.asList(DaoEnum.values()));
    }
    
    public String getValue() {
        return value;
    }
    
    public String getDesc() {
        return desc;
    }
}

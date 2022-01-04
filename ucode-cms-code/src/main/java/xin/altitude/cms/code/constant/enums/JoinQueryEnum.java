package xin.altitude.cms.code.constant.enums;

/**
 * 连接查询类型
 *
 * @author explore
 * @since 2021/12/03 11:50
 **/
public enum JoinQueryEnum {
    /**
     * 一对一查询
     */
    ONE_ONE(11, "一对一查询"),
    /**
     * 一对多查询
     */
    ONE_MORE(12, "一对多查询"),
    /**
     * 多对多查询
     */
    MORE_MORE(22, "多对多查询");
    
    
    private final Integer value;
    private final String desc;
    
    JoinQueryEnum(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }
    
    public Integer getValue() {
        return value;
    }
    
    public String getDesc() {
        return desc;
    }
}

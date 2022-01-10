package xin.altitude.cms.code.constant;

/**
 * 数据标准化字段枚举
 *
 * @author explore
 * @since 2019/11/23 15:38
 **/
public enum DdlEnum {
    /**
     * 创建时间
     */
    create_time,
    /**
     * 更新时间
     */
    update_time,
    /**
     * 逻辑删除
     */
    deleted;
    
    
    @Override
    public String toString() {
        return name();
    }
}

package xin.altitude.cms.code.constant;

/**
 * 数据标准化字段枚举
 *
 * @author explore
 * @since 2021/11/23 15:38
 **/
public enum DdlEnum {
    create_time, update_time, deleted;
    
    
    @Override
    public String toString() {
        return name();
    }
}

package xin.altitude.cms.job.constant;

/**
 * 任务状态
 *
 * @author explore
 * @since 2021/01/18 09:42
 **/
public enum JobStatus {
    /**
     * 正常
     */
    NORMAL("0"),
    /**
     * 暂停
     */
    PAUSE("1");
    
    private final String value;
    
    JobStatus(String value) {
        this.value = value;
    }
    
    public String getValue() {
        return value;
    }
}

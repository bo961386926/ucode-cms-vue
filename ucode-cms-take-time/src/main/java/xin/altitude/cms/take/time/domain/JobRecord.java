package xin.altitude.cms.take.time.domain;

/**
 * 记录定时任务实体类
 *
 * @author 塞泰先生
 * @since 2023/10/10 18:54
 **/
public class JobRecord {
    /**
     * 序号（主键）
     */
    private Long id;
    /**
     * 任务名称（用来区分不同的任务）
     */
    private String jobName;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 持续时间（可视化转换）
     */
    private String duration;
    /**
     * 额外补充信息（JSON数据）
     */
    private String extra;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }
}

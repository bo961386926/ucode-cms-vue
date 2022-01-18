package xin.altitude.cms.job.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.quartz.JobDataMap;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.transaction.annotation.Transactional;
import xin.altitude.cms.common.util.SpringUtils;
import xin.altitude.cms.job.config.ScheduleConfig;
import xin.altitude.cms.job.constant.JobStatus;
import xin.altitude.cms.job.constant.ScheduleConstants;
import xin.altitude.cms.job.domain.SysJob;
import xin.altitude.cms.job.exception.TaskException;
import xin.altitude.cms.job.mapper.SysJobMapper;
import xin.altitude.cms.job.service.ISysJobService;
import xin.altitude.cms.job.util.CronUtils;
import xin.altitude.cms.job.util.QuartzUtils;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 定时任务调度信息 服务层
 *
 * @author ucode
 */
// @Service
public class SysJobServiceImpl extends ServiceImpl<SysJobMapper, SysJob> implements ISysJobService {
    /**
     * 调度器（核心参数）
     */
    private final Scheduler scheduler = SpringUtils.getBean(ScheduleConfig.SCHEDULER_FACTORYBEAN);
    
    /**
     * 项目启动时，初始化定时器
     * 主要是防止手动修改数据库导致未同步到定时任务处理
     * （注：不能手动修改数据库ID和任务组名，否则会导致脏数据）
     */
    @PostConstruct
    public void init() throws SchedulerException, TaskException {
        scheduler.clear();
        List<SysJob> jobList = list();
        for (SysJob job : jobList) {
            QuartzUtils.createScheduleJob(scheduler, job);
        }
    }
    
    /**
     * 获取quartz调度器的计划任务列表
     *
     * @param job 调度信息
     * @return 任务列表
     */
    @Override
    public List<SysJob> selectJobList(SysJob job) {
        return list(Wrappers.lambdaQuery(job));
    }
    
    /**
     * 通过调度任务ID查询调度信息
     *
     * @param jobId 调度任务ID
     * @return 调度任务对象信息
     */
    @Override
    public SysJob selectJobById(Long jobId) {
        return getById(jobId);
    }
    
    /**
     * 暂停任务
     *
     * @param job 调度信息
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean pauseJob(SysJob job) throws SchedulerException {
        Long jobId = job.getJobId();
        String jobGroup = job.getJobGroup();
        job.setStatus(JobStatus.PAUSE.getValue());
        boolean rows = updateById(job);
        if (rows) {
            scheduler.pauseJob(QuartzUtils.createJobKey(jobId, jobGroup));
        }
        return rows;
    }
    
    /**
     * 恢复任务
     *
     * @param job 调度信息
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean resumeJob(SysJob job) throws SchedulerException {
        Long jobId = job.getJobId();
        String jobGroup = job.getJobGroup();
        job.setStatus(JobStatus.NORMAL.getValue());
        boolean rows = updateById(job);
        if (rows) {
            scheduler.resumeJob(QuartzUtils.createJobKey(jobId, jobGroup));
        }
        return rows;
    }
    
    /**
     * 删除任务后，所对应的trigger也将被删除
     *
     * @param job 调度信息
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteJob(SysJob job) throws SchedulerException {
        Long jobId = job.getJobId();
        String jobGroup = job.getJobGroup();
        boolean rows = removeById(jobId);
        if (rows) {
            scheduler.deleteJob(QuartzUtils.createJobKey(jobId, jobGroup));
        }
        return rows;
    }
    
    /**
     * 批量删除调度信息
     *
     * @param jobIds 需要删除的任务ID
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteJobByIds(Long[] jobIds) throws SchedulerException {
        for (Long jobId : jobIds) {
            SysJob job = getById(jobId);
            deleteJob(job);
        }
    }
    
    /**
     * 任务调度状态修改
     *
     * @param job 调度信息
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean changeStatus(SysJob job) throws SchedulerException {
        boolean rows = false;
        String status = job.getStatus();
        if (JobStatus.NORMAL.getValue().equals(status)) {
            rows = resumeJob(job);
        } else if (JobStatus.PAUSE.getValue().equals(status)) {
            rows = pauseJob(job);
        }
        return rows;
    }
    
    /**
     * 立即运行任务
     *
     * @param job 调度信息
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void run(SysJob job) throws SchedulerException {
        Long jobId = job.getJobId();
        String jobGroup = job.getJobGroup();
        SysJob properties = selectJobById(job.getJobId());
        // 参数
        JobDataMap dataMap = new JobDataMap();
        dataMap.put(ScheduleConstants.TASK_PROPERTIES, properties);
        scheduler.triggerJob(QuartzUtils.createJobKey(jobId, jobGroup), dataMap);
    }
    
    /**
     * 新增任务
     *
     * @param job 调度信息 调度信息
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insertJob(SysJob job) throws SchedulerException, TaskException {
        job.setStatus(JobStatus.PAUSE.getValue());
        boolean rows = save(job);
        if (rows) {
            QuartzUtils.createScheduleJob(scheduler, job);
        }
        return rows;
    }
    
    /**
     * 更新任务的时间表达式
     *
     * @param job 调度信息
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateJob(SysJob job) throws SchedulerException, TaskException {
        SysJob properties = selectJobById(job.getJobId());
        boolean rows = updateById(job);
        if (rows) {
            updateSchedulerJob(job, properties.getJobGroup());
        }
        return rows;
    }
    
    /**
     * 更新任务
     *
     * @param job      任务对象
     * @param jobGroup 任务组名
     */
    public void updateSchedulerJob(SysJob job, String jobGroup) throws SchedulerException, TaskException {
        Long jobId = job.getJobId();
        // 判断是否存在
        JobKey jobKey = QuartzUtils.createJobKey(jobId, jobGroup);
        if (scheduler.checkExists(jobKey)) {
            // 防止创建时存在数据问题 先移除，然后在执行创建操作
            scheduler.deleteJob(jobKey);
        }
        QuartzUtils.createScheduleJob(scheduler, job);
    }
    
    /**
     * 校验cron表达式是否有效
     *
     * @param cronExpression 表达式
     * @return 结果
     */
    @Override
    public boolean checkCronExpressionIsValid(String cronExpression) {
        return CronUtils.isValid(cronExpression);
    }
}

package xin.altitude.cms.job.util;

import org.quartz.*;
import xin.altitude.cms.job.constant.ScheduleConstants;
import xin.altitude.cms.job.domain.SysJob;
import xin.altitude.cms.job.exception.TaskException;

/**
 * 定时任务工具类
 *
 * @author ucode
 */
public class QuartzUtils {
    /**
     * 得到quartz任务类
     *
     * @param sysJob 执行计划
     * @return 具体执行任务类
     */
    private static Class<? extends Job> getQuartzJobClass(SysJob sysJob) {
        boolean isConcurrent = "0".equals(sysJob.getConcurrent());
        return isConcurrent ? QuartzJobExecution.class : QuartzDisallowConcurrentExecution.class;
    }
    
    /**
     * 构建任务触发对象
     */
    public static TriggerKey createTriggerKey(Long jobId, String jobGroup) {
        return TriggerKey.triggerKey(ScheduleConstants.TASK_CLASS_NAME + jobId, jobGroup);
    }
    
    /**
     * 构建任务键对象
     *
     * @param jobId    任务ID（用户自定义编号（一般唯一））
     * @param jobGroup 任务组别
     * @return JobKey
     */
    public static JobKey createJobKey(Long jobId, String jobGroup) {
        return JobKey.jobKey(ScheduleConstants.TASK_CLASS_NAME + jobId, jobGroup);
    }
    
    /**
     * 创建定时任务
     *
     * @param scheduler
     * @param sysJob
     * @throws SchedulerException
     * @throws TaskException
     */
    public static void createScheduleJob(Scheduler scheduler, SysJob sysJob) throws SchedulerException, TaskException {
        /* 构造QZ Job */
        Class<? extends Job> jobClass = getQuartzJobClass(sysJob);
        // 构建job信息
        Long jobId = sysJob.getJobId();
        String jobGroup = sysJob.getJobGroup();
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(createJobKey(jobId, jobGroup)).build();
        
        // 表达式调度构建器
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(sysJob.getCronExpression());
        // cronScheduleBuilder = handleCronScheduleMisfirePolicy(sysJob, cronScheduleBuilder);
        handleCronScheduleMisfirePolicy(sysJob, cronScheduleBuilder);
        
        // 按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(createTriggerKey(jobId, jobGroup))
                .withSchedule(cronScheduleBuilder).build();
        
        // 放入参数，运行时的方法可以获取
        jobDetail.getJobDataMap().put(ScheduleConstants.TASK_PROPERTIES, sysJob);
        
        // 判断是否存在
        if (scheduler.checkExists(createJobKey(jobId, jobGroup))) {
            // 防止创建时存在数据问题 先移除，然后在执行创建操作
            scheduler.deleteJob(createJobKey(jobId, jobGroup));
        }
        
        scheduler.scheduleJob(jobDetail, trigger);
        
        if (sysJob.getStatus().equals(ScheduleConstants.Status.PAUSE.getValue())) {
            /* 暂停任务 */
            scheduler.pauseJob(QuartzUtils.createJobKey(jobId, jobGroup));
        }
    }
    
    // /**
    //  * 设置定时任务策略
    //  */
    // public static CronScheduleBuilder handleCronScheduleMisfirePolicy(SysJob sysJob, CronScheduleBuilder cb)
    //         throws TaskException {
    //     switch (sysJob.getMisfirePolicy()) {
    //         case ScheduleConstants.MISFIRE_DEFAULT:
    //             return cb;
    //         case ScheduleConstants.MISFIRE_IGNORE_MISFIRES:
    //             return cb.withMisfireHandlingInstructionIgnoreMisfires();
    //         case ScheduleConstants.MISFIRE_FIRE_AND_PROCEED:
    //             return cb.withMisfireHandlingInstructionFireAndProceed();
    //         case ScheduleConstants.MISFIRE_DO_NOTHING:
    //             return cb.withMisfireHandlingInstructionDoNothing();
    //         default:
    //             throw new TaskException("The task misfire policy '" + sysJob.getMisfirePolicy()
    //                     + "' cannot be used in cron schedule tasks", TaskException.Code.CONFIG_ERROR);
    //     }
    // }
    
    /**
     * 设置定时任务策略
     */
    public static void handleCronScheduleMisfirePolicy(SysJob sysJob, CronScheduleBuilder cb)
            throws TaskException {
        switch (sysJob.getMisfirePolicy()) {
            case ScheduleConstants.MISFIRE_DEFAULT:
                return;
            case ScheduleConstants.MISFIRE_IGNORE_MISFIRES:
                cb.withMisfireHandlingInstructionIgnoreMisfires();
                return;
            case ScheduleConstants.MISFIRE_FIRE_AND_PROCEED:
                cb.withMisfireHandlingInstructionFireAndProceed();
                return;
            case ScheduleConstants.MISFIRE_DO_NOTHING:
                cb.withMisfireHandlingInstructionDoNothing();
                return;
            default:
                throw new TaskException("The task misfire policy '" + sysJob.getMisfirePolicy()
                        + "' cannot be used in cron schedule tasks", TaskException.Code.CONFIG_ERROR);
        }
    }
}

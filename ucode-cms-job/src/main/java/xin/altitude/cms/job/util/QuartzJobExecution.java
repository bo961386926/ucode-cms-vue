/*
 * Copyright (Java知识图谱) 2022.
 */

package xin.altitude.cms.job.util;

import org.quartz.JobExecutionContext;
import xin.altitude.cms.job.domain.SysJob;

/**
 * 定时任务处理（允许并发执行）
 *
 * @author ucode
 */
public class QuartzJobExecution extends AbstractQuartzJob {
    @Override
    protected void doExecute(JobExecutionContext context, SysJob sysJob) throws Exception {
        JobInvokeUtil.invokeMethod(sysJob);
    }
}

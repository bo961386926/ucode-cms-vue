/*
 *
 * Copyright (c) 2020-2022, Java知识图谱 (http://www.altitude.xin).
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package xin.altitude.cms.job.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.commons.lang3.StringUtils;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xin.altitude.cms.common.constant.Constants;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.util.StringUtil;
import xin.altitude.cms.excel.util.ExcelUtil;
import xin.altitude.cms.framework.annotation.Log;
import xin.altitude.cms.framework.config.CmsConfig;
import xin.altitude.cms.framework.constant.enums.BusinessType;
import xin.altitude.cms.job.config.ScheduleConfig;
import xin.altitude.cms.job.domain.SysJob;
import xin.altitude.cms.job.exception.TaskException;
import xin.altitude.cms.job.service.ISysJobService;
import xin.altitude.cms.job.service.impl.SysJobServiceImpl;
import xin.altitude.cms.job.util.CronUtils;

import java.util.List;

/**
 * 调度任务信息操作处理
 *
 * @author ucode
 */
@Import({ScheduleConfig.class, SysJobServiceImpl.class})
@ConditionalOnProperty(value = "ucode.job.enabled", havingValue = "true")
@ResponseBody
@RequestMapping(CmsConfig.UNIFORM_PREFIX + "/monitor/job")
public class SysJobController {
    @Autowired
    private ISysJobService jobService;

    /**
     * 查询定时任务列表
     *
     * @return
     */
    @GetMapping("/list")
    public AjaxResult list(Page<SysJob> page, SysJob sysJob) {
        return AjaxResult.success(jobService.page(page, Wrappers.lambdaQuery(sysJob)));
    }

    /**
     * 导出定时任务列表
     */
    @Log(title = "定时任务", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysJob sysJob) {
        List<SysJob> list = jobService.selectJobList(sysJob);
        ExcelUtil<SysJob> util = new ExcelUtil<SysJob>(SysJob.class);
        return util.exportExcel(list, "定时任务");
    }

    /**
     * 获取定时任务详细信息
     */
    @GetMapping(value = "/{jobId}")
    public AjaxResult getInfo(@PathVariable("jobId") Long jobId) {
        return AjaxResult.success(jobService.selectJobById(jobId));
    }

    /**
     * 新增定时任务
     */
    @Log(title = "定时任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysJob job) throws SchedulerException, TaskException {
        if (!CronUtils.isValid(job.getCronExpression())) {
            return AjaxResult.error("新增任务'" + job.getJobName() + "'失败，Cron表达式不正确");
        } else if (StringUtils.containsIgnoreCase(job.getInvokeTarget(), Constants.LOOKUP_RMI)) {
            return AjaxResult.error("新增任务'" + job.getJobName() + "'失败，目标字符串不允许'rmi://'调用");
        } else if (StringUtils.containsIgnoreCase(job.getInvokeTarget(), Constants.LOOKUP_LDAP)) {
            return AjaxResult.error("新增任务'" + job.getJobName() + "'失败，目标字符串不允许'ldap://'调用");
        } else if (StringUtil.containsAnyIgnoreCase(job.getInvokeTarget(), new String[]{Constants.HTTP, Constants.HTTPS})) {
            return AjaxResult.error("新增任务'" + job.getJobName() + "'失败，目标字符串不允许'http(s)//'调用");
        }
        // job.setCreateBy(getUsername());
        return AjaxResult.success(jobService.insertJob(job));
    }

    /**
     * 修改定时任务
     */
    @Log(title = "定时任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysJob job) throws SchedulerException, TaskException {
        if (!CronUtils.isValid(job.getCronExpression())) {
            return AjaxResult.error("修改任务'" + job.getJobName() + "'失败，Cron表达式不正确");
        } else if (StringUtils.containsIgnoreCase(job.getInvokeTarget(), Constants.LOOKUP_RMI)) {
            return AjaxResult.error("修改任务'" + job.getJobName() + "'失败，目标字符串不允许'rmi://'调用");
        } else if (StringUtils.containsIgnoreCase(job.getInvokeTarget(), Constants.LOOKUP_LDAP)) {
            return AjaxResult.error("修改任务'" + job.getJobName() + "'失败，目标字符串不允许'ldap://'调用");
        } else if (StringUtil.containsAnyIgnoreCase(job.getInvokeTarget(), new String[]{Constants.HTTP, Constants.HTTPS})) {
            return AjaxResult.error("修改任务'" + job.getJobName() + "'失败，目标字符串不允许'http(s)//'调用");
        }
        // job.setUpdateBy(getUsername());
        return AjaxResult.success(jobService.updateJob(job));
    }

    /**
     * 定时任务状态修改
     */
    @Log(title = "定时任务", businessType = BusinessType.UPDATE)
    @PutMapping("/changeStatus")
    public AjaxResult changeStatus(@RequestBody SysJob job) throws SchedulerException {
        SysJob newJob = new SysJob();
        newJob.setJobId(job.getJobId());
        newJob.setStatus(job.getStatus());
        return AjaxResult.success(jobService.changeStatus(newJob));
    }

    /**
     * 定时任务立即执行一次
     */
    @Log(title = "定时任务", businessType = BusinessType.UPDATE)
    @PutMapping("/run")
    public AjaxResult run(@RequestBody SysJob job) throws SchedulerException {
        jobService.run(job);
        return AjaxResult.success();
    }

    /**
     * 删除定时任务
     */
    @Log(title = "定时任务", businessType = BusinessType.DELETE)
    @DeleteMapping("/{jobIds}")
    public AjaxResult remove(@PathVariable Long[] jobIds) throws SchedulerException {
        jobService.deleteJobByIds(jobIds);
        return AjaxResult.success();
    }
}

/*
 * Copyright (Java知识图谱) 2022.
 */

package xin.altitude.cms.job.service;

import com.baomidou.mybatisplus.extension.service.IService;
import xin.altitude.cms.job.domain.SysJobLog;

import java.util.List;

/**
 * 定时任务调度日志信息信息 服务层
 *
 * @author ucode
 */
public interface ISysJobLogService extends IService<SysJobLog> {
    /**
     * 获取quartz调度器日志的计划任务
     *
     * @param jobLog 调度日志信息
     * @return 调度任务日志集合
     */
    List<SysJobLog> selectJobLogList(SysJobLog jobLog);
    
    /**
     * 通过调度任务日志ID查询调度信息
     *
     * @param jobLogId 调度任务日志ID
     * @return 调度任务日志对象信息
     */
    SysJobLog selectJobLogById(Long jobLogId);
    
    /**
     * 新增任务日志
     *
     * @param jobLog 调度日志信息
     */
    void addJobLog(SysJobLog jobLog);
    
    /**
     * 批量删除调度日志信息
     *
     * @param logIds 需要删除的日志ID
     * @return 结果
     */
    boolean deleteJobLogByIds(Long[] logIds);
    
    /**
     * 删除任务日志
     *
     * @param jobId 调度日志ID
     * @return 结果
     */
    boolean deleteJobLogById(Long jobId);
    
    /**
     * 清空任务日志
     */
    void cleanJobLog();
}

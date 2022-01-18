/*
 * Copyright (Java知识图谱) 2022.
 */

package xin.altitude.cms.job.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import xin.altitude.cms.job.domain.SysJobLog;
import xin.altitude.cms.job.mapper.SysJobLogMapper;
import xin.altitude.cms.job.service.ISysJobLogService;

import java.util.Arrays;
import java.util.List;

/**
 * 定时任务调度日志信息 服务层
 *
 * @author ucode
 */
// @Service
public class SysJobLogServiceImpl extends ServiceImpl<SysJobLogMapper, SysJobLog> implements ISysJobLogService {
    
    /**
     * 获取quartz调度器日志的计划任务
     *
     * @param jobLog 调度日志信息
     * @return 调度任务日志集合
     */
    @Override
    public List<SysJobLog> selectJobLogList(SysJobLog jobLog) {
        return list(Wrappers.lambdaQuery(jobLog));
    }
    
    /**
     * 通过调度任务日志ID查询调度信息
     *
     * @param jobLogId 调度任务日志ID
     * @return 调度任务日志对象信息
     */
    @Override
    public SysJobLog selectJobLogById(Long jobLogId) {
        return getById(jobLogId);
    }
    
    /**
     * 新增任务日志
     *
     * @param jobLog 调度日志信息
     */
    @Override
    public void addJobLog(SysJobLog jobLog) {
        save(jobLog);
    }
    
    /**
     * 批量删除调度日志信息
     *
     * @param logIds 需要删除的数据ID
     * @return 结果
     */
    @Override
    public boolean deleteJobLogByIds(Long[] logIds) {
        return removeBatchByIds(Arrays.asList(logIds));
    }
    
    /**
     * 删除任务日志
     *
     * @param jobId 调度日志ID
     * @return
     */
    @Override
    public boolean deleteJobLogById(Long jobId) {
        return removeById(jobId);
    }
    
    /**
     * 清空任务日志
     */
    @Override
    public void cleanJobLog() {
        /* 通过循环清空表（防止非人为意外清空表） */
        do {
            remove(Wrappers.lambdaQuery(SysJobLog.class).gt(SysJobLog::getJobLogId, 0).last("limit 100"));
            // remove(Wrappers.lambdaQuery(SysJobLog.class).last("limit 100"));
        } while (count() > 0);
    }
}

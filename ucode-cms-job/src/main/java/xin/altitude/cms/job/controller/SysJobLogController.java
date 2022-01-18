package xin.altitude.cms.job.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import xin.altitude.cms.auth.controller.BaseProController;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.excel.util.ExcelUtil;
import xin.altitude.cms.framework.annotation.Log;
import xin.altitude.cms.framework.config.CmsConfig;
import xin.altitude.cms.framework.constant.enums.BusinessType;
import xin.altitude.cms.framework.core.page.TableDataInfo;
import xin.altitude.cms.job.domain.SysJobLog;
import xin.altitude.cms.job.service.ISysJobLogService;
import xin.altitude.cms.job.service.impl.SysJobLogServiceImpl;

import java.util.List;

/**
 * 调度日志操作处理
 *
 * @author ucode
 */
@Import({SysJobLogServiceImpl.class})
@ConditionalOnProperty(value = "ucode.job.enabled", havingValue = "true")
// @RestController
@ResponseBody
@RequestMapping(CmsConfig.UNIFORM_PREFIX + "/monitor/jobLog")
public class SysJobLogController extends BaseProController {
    @Autowired
    private ISysJobLogService jobLogService;
    
    /**
     * 查询定时任务调度日志列表
     */
    @GetMapping("/list")
    public TableDataInfo list(SysJobLog sysJobLog) {
        startPage();
        List<SysJobLog> list = jobLogService.selectJobLogList(sysJobLog);
        return getDataTable(list);
    }
    
    /**
     * 导出定时任务调度日志列表
     */
    @Log(title = "任务调度日志", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(SysJobLog sysJobLog) {
        List<SysJobLog> list = jobLogService.selectJobLogList(sysJobLog);
        ExcelUtil<SysJobLog> util = new ExcelUtil<SysJobLog>(SysJobLog.class);
        return util.exportExcel(list, "调度日志");
    }
    
    /**
     * 根据调度编号获取详细信息
     */
    @GetMapping(value = "/{jobLogId}")
    public AjaxResult getInfo(@PathVariable Long jobLogId) {
        return AjaxResult.success(jobLogService.selectJobLogById(jobLogId));
    }
    
    
    /**
     * 删除定时任务调度日志
     */
    @Log(title = "定时任务调度日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{jobLogIds}")
    public AjaxResult remove(@PathVariable Long[] jobLogIds) {
        return toAjax(jobLogService.deleteJobLogByIds(jobLogIds));
    }
    
    /**
     * 清空定时任务调度日志
     */
    @Log(title = "调度日志", businessType = BusinessType.CLEAN)
    @DeleteMapping("/clean")
    public AjaxResult clean() {
        jobLogService.cleanJobLog();
        return AjaxResult.success();
    }
}

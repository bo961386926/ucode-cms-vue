package xin.altitude.cms.system.controller;

// import lombok.extern.java.Log;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.altitude.cms.auth.controller.BaseController;
import xin.altitude.cms.common.annotation.Log;
import xin.altitude.cms.common.constant.enums.BusinessType;
import xin.altitude.cms.common.core.domain.AjaxResult;
import xin.altitude.cms.common.util.poi.ExcelUtil;
import xin.altitude.cms.system.domain.SysLogininfor;
import xin.altitude.cms.system.service.ISysLogininforService;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 系统访问记录
 *
 * @author ucode
 */
@RestController
@RequestMapping("/monitor/logininfor")
public class SysLogininforController extends BaseController {
    @Autowired
    private ISysLogininforService logininforService;
    
    // @PreAuthorize("@ss.hasPermi('monitor:logininfor:list')")
    @GetMapping("/list")
    public AjaxResult list(Page<SysLogininfor> page, SysLogininfor logininfor) {
        // startPage();
        // List<SysLogininfor> list = logininforService.selectLogininforList(logininfor);
        // return getDataTable(list);
        return AjaxResult.success(logininforService.page(page, Wrappers.lambdaQuery(logininfor)));
    }
    
    @Log(title = "登录日志", businessType = BusinessType.EXPORT)
    // @PreAuthorize("@ss.hasPermi('monitor:logininfor:export')")
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysLogininfor logininfor) {
        List<SysLogininfor> list = logininforService.selectLogininforList(logininfor);
        ExcelUtil<SysLogininfor> util = new ExcelUtil<SysLogininfor>(SysLogininfor.class);
        util.exportExcel(response, list, "登录日志");
    }
    
    // @PreAuthorize("@ss.hasPermi('monitor:logininfor:remove')")
    @Log(title = "登录日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{infoIds}")
    public AjaxResult remove(@PathVariable Long[] infoIds) {
        return toAjax(logininforService.deleteLogininforByIds(infoIds));
    }
    
    // @PreAuthorize("@ss.hasPermi('monitor:logininfor:remove')")
    @Log(title = "登录日志", businessType = BusinessType.CLEAN)
    @DeleteMapping("/clean")
    public AjaxResult clean() {
        logininforService.cleanLogininfor();
        return AjaxResult.success();
    }
}

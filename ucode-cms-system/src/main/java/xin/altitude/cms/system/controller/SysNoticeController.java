package xin.altitude.cms.system.controller;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xin.altitude.cms.auth.controller.BaseController;
import xin.altitude.cms.common.annotation.Log;
import xin.altitude.cms.common.core.domain.AjaxResult;
import xin.altitude.cms.common.enums.BusinessType;
import xin.altitude.cms.system.domain.SysNotice;
import xin.altitude.cms.system.service.ISysNoticeService;

/**
 * 公告 信息操作处理
 *
 * @author ucode
 */
@RestController
@RequestMapping("/system/notice")
public class SysNoticeController extends BaseController {
    @Autowired
    private ISysNoticeService noticeService;
    
    /**
     * 获取通知公告列表
     *
     * @return
     */
    // @PreAuthorize("@ss.hasPermi('system:notice:list')")
    @GetMapping("/list")
    public AjaxResult list(Page<SysNotice> page, SysNotice notice) {
        // startPage();
        // List<SysNotice> list = noticeService.selectNoticeList(notice);
        // return getDataTable(list);
        return AjaxResult.success(noticeService.page(page, Wrappers.lambdaQuery(notice)));
    }
    
    /**
     * 根据通知公告编号获取详细信息
     */
    // @PreAuthorize("@ss.hasPermi('system:notice:query')")
    @GetMapping(value = "/{noticeId}")
    public AjaxResult getInfo(@PathVariable Long noticeId) {
        return AjaxResult.success(noticeService.selectNoticeById(noticeId));
    }
    
    /**
     * 新增通知公告
     */
    // @PreAuthorize("@ss.hasPermi('system:notice:add')")
    @Log(title = "通知公告", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@Validated @RequestBody SysNotice notice) {
        notice.setCreateBy(getUsername());
        return toAjax(noticeService.insertNotice(notice));
    }
    
    /**
     * 修改通知公告
     */
    // @PreAuthorize("@ss.hasPermi('system:notice:edit')")
    @Log(title = "通知公告", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@Validated @RequestBody SysNotice notice) {
        notice.setUpdateBy(getUsername());
        return toAjax(noticeService.updateNotice(notice));
    }
    
    /**
     * 删除通知公告
     */
    // @PreAuthorize("@ss.hasPermi('system:notice:remove')")
    @Log(title = "通知公告", businessType = BusinessType.DELETE)
    @DeleteMapping("/{noticeIds}")
    public AjaxResult remove(@PathVariable Long[] noticeIds) {
        return toAjax(noticeService.deleteNoticeByIds(noticeIds));
    }
}

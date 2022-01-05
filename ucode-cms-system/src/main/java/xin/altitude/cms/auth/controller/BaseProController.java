package xin.altitude.cms.auth.controller;

import com.github.pagehelper.PageHelper;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import xin.altitude.cms.auth.model.LoginUser;
import xin.altitude.cms.auth.util.SecurityUtils;
import xin.altitude.cms.common.controller.BaseController;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.common.util.StringUtil;
import xin.altitude.cms.framework.constant.HttpStatus;
import xin.altitude.cms.framework.core.page.PageDomain;
import xin.altitude.cms.framework.core.page.TableDataInfo;
import xin.altitude.cms.framework.core.page.TableSupport;
import xin.altitude.cms.framework.util.DateUtils;
import xin.altitude.cms.framework.util.sql.SqlUtil;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;

/**
 * web层通用数据处理
 *
 * @author ucode
 */
public class BaseProController extends BaseController {
    /**
     * 设置请求分页数据
     */
    protected void startPage() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtil.isNotNull(pageNum) && StringUtil.isNotNull(pageSize)) {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            Boolean reasonable = pageDomain.getReasonable();
            PageHelper.startPage(pageNum, pageSize, orderBy).setReasonable(reasonable);
        }
    }
    
    /**
     * 设置请求排序数据
     */
    protected void startOrderBy() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        if (StringUtil.isNotEmpty(pageDomain.getOrderBy())) {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.orderBy(orderBy);
        }
    }
    
    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected TableDataInfo getDataTable(List<?> list) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(HttpStatus.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setData(new TableDataInfo.DataBody(list, list.size()));
        // rspData.setRows(list);
        // rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }
    
    /**
     * 返回失败消息
     */
    public AjaxResult error() {
        return AjaxResult.error();
    }
    
    /**
     * 返回成功消息
     */
    public AjaxResult success(String message) {
        return AjaxResult.success(message);
    }
    
    /**
     * 返回失败消息
     */
    public AjaxResult error(String message) {
        return AjaxResult.error(message);
    }
    
    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected AjaxResult toAjax(int rows) {
        return rows > 0 ? AjaxResult.success() : AjaxResult.error();
    }
    
    /**
     * 响应返回结果
     *
     * @param result 结果
     * @return 操作结果
     */
    protected AjaxResult toAjax(boolean result) {
        return result ? success() : error();
    }
    
    /**
     * 页面跳转
     */
    public String redirect(String url) {
        return StringUtil.format("redirect:{}", url);
    }
    
    /**
     * 获取用户缓存信息
     */
    public LoginUser getLoginUser() {
        return SecurityUtils.getLoginUser();
    }
    
    /**
     * 获取登录用户id
     */
    public Long getUserId() {
        return getLoginUser().getUserId();
    }
    
    /**
     * 获取登录部门id
     */
    public Long getDeptId() {
        return getLoginUser().getDeptId();
    }
    
    /**
     * 获取登录用户名
     */
    public String getUsername() {
        return getLoginUser().getUsername();
    }
    
    /**
     * 将前台传递过来的
     *
     * @param binder 数据绑定器
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        /* Date 类型转换 */
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
        });
    }
}
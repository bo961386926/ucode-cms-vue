package xin.altitude.cms.common.controller;

import xin.altitude.cms.common.entity.AjaxResult;

/**
 * @author explore
 * @since 2021/12/31 13:13
 **/
public abstract class BaseController {
    
    /**
     * 返回成功
     *
     * @return AjaxResult
     */
    public AjaxResult success() {
        return AjaxResult.success();
    }
    
    /**
     * 返回成功
     *
     * @param data 具体响应体数据
     * @return AjaxResult
     */
    public AjaxResult success(Object data) {
        return AjaxResult.success(data);
    }
}

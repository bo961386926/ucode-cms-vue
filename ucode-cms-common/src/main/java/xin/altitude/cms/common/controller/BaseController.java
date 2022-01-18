/*
 * Copyright (Java知识图谱) 2022.
 */

package xin.altitude.cms.common.controller;

import xin.altitude.cms.common.entity.AjaxResult;

/**
 * @author explore
 * @since 2019/12/31 13:13
 **/
public abstract class BaseController {
    
    /**
     * 返回成功
     *
     * @return AjaxResult
     */
    protected AjaxResult success() {
        return AjaxResult.success();
    }
    
    /**
     * 返回成功
     *
     * @param data 具体响应体数据
     * @return AjaxResult
     */
    protected AjaxResult success(Object data) {
        return AjaxResult.success(data);
    }
    
    /**
     * 响应返回结果
     *
     * @param result 结果
     * @return 操作结果
     */
    protected AjaxResult toAjax(boolean result) {
        return result ? AjaxResult.success() : AjaxResult.error();
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
}

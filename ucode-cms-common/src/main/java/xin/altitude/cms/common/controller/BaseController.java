

/*
 *
 * Copyright (c) 2020-2022, 赛泰先生 (http://www.altitude.xin).
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

package xin.altitude.cms.common.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import xin.altitude.cms.common.converter.DateConverter;
import xin.altitude.cms.common.converter.ListConverter;
import xin.altitude.cms.common.entity.AjaxResult;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;

/**
 * @author <a href="http://www.altitude.xin" target="_blank">赛泰先生</a>
 * @author <a href="https://gitee.com/decsa/ucode-cms-vue" target="_blank">UCode CMS</a>
 * @author <a href="https://space.bilibili.com/1936685014" target="_blank">B站视频</a>
 * @since 2019/12/31 13:13
 **/
public abstract class BaseController {
    private Logger logger = LoggerFactory.getLogger(getClass());


    /**
     * 控制器接收{@link Date}类型参数
     */
    @InitBinder
    public void dateType(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(new DateConverter().convert(text));
            }
        });
    }

    /**
     * 控制器接收{@link List}类型参数
     */
    @InitBinder
    public void listType(WebDataBinder binder) {
        binder.registerCustomEditor(List.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                setValue(new ListConverter().convert(text));
            }
        });
    }

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

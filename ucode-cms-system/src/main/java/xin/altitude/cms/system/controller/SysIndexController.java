/*
 *
 *  *
 *  *  Copyright (c) 2020-2022, Java知识图谱 (http://www.altitude.xin).
 *  *
 *  *  Licensed under the Apache License, Version 2.0 (the "License");
 *  *  you may not use this file except in compliance with the License.
 *  *  You may obtain a copy of the License at
 *  *
 *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *
 *  *  Unless required by applicable law or agreed to in writing, software
 *  *  distributed under the License is distributed on an "AS IS" BASIS,
 *  *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  See the License for the specific language governing permissions and
 *  *  limitations under the License.
 *  *
 *
 */

package xin.altitude.cms.system.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import xin.altitude.cms.common.util.StringUtil;
import xin.altitude.cms.framework.config.CmsConfig;

/**
 * 首页
 *
 * @author ucode
 */
// @ResponseBody
public class SysIndexController {
    /**
     * 系统基础配置
     */
    @Autowired
    private CmsConfig cmsConfig;

    /**
     * 访问首页，提示语
     */
    // @RequestMapping("/")
    @GetMapping("/")
    public String index() {
        return StringUtil.format("欢迎使用{}后台管理框架，当前版本：v{}，请通过前端地址访问。", cmsConfig.getCms().getName(), cmsConfig.getCms().getVersion());
    }
}

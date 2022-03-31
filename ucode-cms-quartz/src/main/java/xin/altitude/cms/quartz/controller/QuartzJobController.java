/*
 *
 * Copyright (c) 2020-2022, Java知识图谱 (http://www.altitude.xin).
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

package xin.altitude.cms.quartz.controller;

import org.quartz.JobDataMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import xin.altitude.cms.common.constant.Constants;
import xin.altitude.cms.common.entity.AjaxResult;
import xin.altitude.cms.quartz.service.IQuartzJobService;

import java.util.Map;

/**
 * 调度任务信息操作处理
 *
 * @author ucode
 */
@ResponseBody
@RequestMapping(Constants.UNIFORM_PREFIX + "/quartz/job")
public class QuartzJobController {
    @Autowired
    private IQuartzJobService jobService;

    /**
     * 立即运行
     */
    @GetMapping("/{jobId}")
    public AjaxResult run(@PathVariable Long jobId, @RequestParam Map<String, Object> map) {
        jobService.run(jobId, new JobDataMap(map));
        return AjaxResult.success();
    }

}

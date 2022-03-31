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

package xin.altitude.cms.quartz.service;

import org.quartz.JobDataMap;

/**
 * 定时任务调度信息信息 服务层
 *
 * @author ucode
 */
public interface IQuartzJobService {

    /**
     * 立即运行任务
     *
     * @param jobId 任务ID
     */
    void run(long jobId);

    void run(long jobId, JobDataMap jobDataMap);
}

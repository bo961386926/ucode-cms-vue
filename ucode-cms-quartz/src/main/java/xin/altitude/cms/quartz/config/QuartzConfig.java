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

package xin.altitude.cms.quartz.config;

import org.quartz.Job;
import org.quartz.Scheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import xin.altitude.cms.common.util.SpringUtils;
import xin.altitude.cms.quartz.constant.ScheduleConstants;
import xin.altitude.cms.quartz.util.QuartzUtils;

import javax.annotation.PostConstruct;

/**
 * @author explore
 * @since 2022/03/30 20:21
 **/
public class QuartzConfig {
    /**
     * 使用SpringBoot自动装载外部配置，初始化调度器
     *
     * @return Scheduler调度器
     * @throws Exception
     */
    @Bean(ScheduleConstants.SCHEDULE_NAME)
    public Scheduler scheduler() throws Exception {
        SchedulerFactoryBean factoryBean = SpringUtils.getBean(SchedulerFactoryBean.class);
        Scheduler scheduler = factoryBean.getScheduler();
        scheduler.start();
        return scheduler;
    }

    /**
     * 初始化并启动任务列表
     */
    @PostConstruct
    public void init() {
        SpringUtils.getBeans(Job.class).forEach(e -> QuartzUtils.createScheduleJob(e.getClass()));
    }
}

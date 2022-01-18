/*
 * Copyright (Java知识图谱) 2022.
 */

package xin.altitude.cms.job.config;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import xin.altitude.cms.common.util.SpringUtils;
import xin.altitude.cms.framework.config.CmsConfig;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * 定时任务配置
 *
 * @author ucode
 */
public class ScheduleConfig {
    public static final String SCHEDULER_FACTORYBEAN = "schedulerFactoryBean";
    
    /**
     * SchedulerFactoryBean
     *
     * @param dataSource 数据库表
     * @return SchedulerFactoryBean
     */
    @Bean(SCHEDULER_FACTORYBEAN)
    public SchedulerFactoryBean schedulerFactoryBean(DataSource dataSource) {
        SchedulerFactoryBean factory = new SchedulerFactoryBean();
        /* 获取定时任务配置 */
        CmsConfig.Job job = SpringUtils.getBean(CmsConfig.class).getJob();
        if (job.getPersist()) {
            factory.setDataSource(dataSource);
        }
        
        Properties prop = getProperties(job.getPersist());
        factory.setQuartzProperties(prop);
        
        factory.setSchedulerName("UCodeCmsScheduler");
        // 延时启动
        factory.setStartupDelay(1);
        factory.setApplicationContextSchedulerContextKey("applicationContextKey");
        // 可选，QuartzScheduler
        // 启动时更新己存在的Job，这样就不用每次修改targetObject后删除qrtz_job_details表对应记录了
        factory.setOverwriteExistingJobs(true);
        // 设置自动启动，默认为true
        factory.setAutoStartup(true);
        
        return factory;
    }
    
    /**
     * 获取Quartz属性配置
     *
     * @param persist 是否持久化到数据库
     * @return Properties
     */
    private Properties getProperties(boolean persist) {
        /* quartz参数 */
        Properties prop = new Properties();
        prop.put("org.quartz.scheduler.instanceName", "UCodeCmsScheduler");
        prop.put("org.quartz.scheduler.instanceId", "AUTO");
        // 线程池配置
        prop.put("org.quartz.threadPool.class", "org.quartz.simpl.SimpleThreadPool");
        prop.put("org.quartz.threadPool.threadCount", "20");
        prop.put("org.quartz.threadPool.threadPriority", "5");
        if (persist) {
            // JobStore配置
            prop.put("org.quartz.jobStore.class", "org.quartz.impl.jdbcjobstore.JobStoreTX");
            // 集群配置
            prop.put("org.quartz.jobStore.isClustered", "true");
            prop.put("org.quartz.jobStore.clusterCheckinInterval", "15000");
            prop.put("org.quartz.jobStore.maxMisfiresToHandleAtATime", "1");
            prop.put("org.quartz.jobStore.txIsolationLevelSerializable", "true");
            
            prop.put("org.quartz.jobStore.misfireThreshold", "12000");
            prop.put("org.quartz.jobStore.tablePrefix", "qrtz_");
        } else {
            // JobStore配置
            prop.put("org.quartz.jobStore.class", "org.quartz.simpl.RAMJobStore");
        }
        
        return prop;
    }
}

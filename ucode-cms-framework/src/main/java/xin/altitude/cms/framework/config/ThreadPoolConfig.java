package xin.altitude.cms.framework.config;

import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import xin.altitude.cms.common.util.SpringUtils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池配置
 *
 * @author ucode
 **/
// @ConditionalOnProperty(value = "ucode.thread.enabled", havingValue = "true")
public class ThreadPoolConfig {
    /**
     * 定时任务线程池名称
     */
    public static final String SCHEDULED_POOL_NAME = "CMS_SCHEDULED_POOL";
    /**
     * 定时任务线程池名称
     */
    public static final String FIXED_POOL_NAME = "CMS_FIXED_POOL";
    /**
     * 定时任务线程池名称
     */
    public static final String CACHED_POOL_NAME = "CMS_CACHED_POOL";
    
    /**
     * 外部配置线程池对象
     */
    private final CmsConfig.Thread thread = SpringUtils.getBean(CmsConfig.class).getThread();
    
    
    /**
     * 执行周期性或定时任务
     *
     * @return ScheduledExecutorService
     */
    @Bean(SCHEDULED_POOL_NAME)
    @ConditionalOnProperty(value = "ucode.thread.scheduleEnabled", havingValue = "true")
    protected ScheduledExecutorService scheduledThreadPool() {
        BasicThreadFactory threadFactory = new BasicThreadFactory.Builder().namingPattern("scheduled-thread-pool-%d").daemon(true).build();
        return new ScheduledThreadPoolExecutor(thread.getCorePoolSize(), threadFactory);
    }
    
    /**
     * 固定大小线程池
     *
     * @return ThreadPoolExecutor
     */
    @Bean(FIXED_POOL_NAME)
    @ConditionalOnProperty(value = "ucode.thread.fixEnabled", havingValue = "true")
    public ThreadPoolExecutor fixedThreadPool() {
        BasicThreadFactory threadFactory = new BasicThreadFactory.Builder().namingPattern("fixed-thread-pool-%d").daemon(true).build();
        return new ThreadPoolExecutor(thread.getCorePoolSize(), thread.getCorePoolSize(), 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), threadFactory);
    }
    
    /**
     * 可伸缩大小线程池
     *
     * @return ExecutorService
     */
    @Bean(CACHED_POOL_NAME)
    @ConditionalOnProperty(value = "ucode.thread.cacheEnabled", havingValue = "true")
    public ExecutorService cachedThreadPool() {
        BasicThreadFactory threadFactory = new BasicThreadFactory.Builder().namingPattern("cached-thread-pool-%d").daemon(true).build();
        return new ThreadPoolExecutor(thread.getCorePoolSize(), thread.getMaxPoolSize(), thread.getKeepAliveSeconds(), TimeUnit.SECONDS, new SynchronousQueue<>(), threadFactory);
    }
}

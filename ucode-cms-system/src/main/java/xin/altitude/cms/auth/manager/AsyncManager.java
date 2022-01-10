package xin.altitude.cms.auth.manager;

import xin.altitude.cms.common.util.SpringUtils;
import xin.altitude.cms.framework.util.Threads;

import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 异步任务管理器
 *
 * @author ucode
 */
public class AsyncManager {
    private static final AsyncManager ME = new AsyncManager();
    private static final int OPERATE_DELAY_TIME = 10;
    /**
     * 异步操作任务调度线程池
     */
    private final ScheduledExecutorService executor = SpringUtils.getBean("scheduledExecutorService");
    
    /**
     * 单例模式
     */
    private AsyncManager() {
    }
    
    public static AsyncManager me() {
        return ME;
    }
    
    /**
     * 执行任务
     *
     * @param task 任务
     */
    public void execute(TimerTask task) {
        /*
         * 操作延迟10毫秒
         */
        
        executor.schedule(task, OPERATE_DELAY_TIME, TimeUnit.MILLISECONDS);
    }
    
    /**
     * 停止任务线程池
     */
    public void shutdown() {
        Threads.shutdownAndAwaitTermination(executor);
    }
}

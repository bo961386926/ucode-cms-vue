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

package xin.altitude.cms.common.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import xin.altitude.cms.common.util.EntityUtils;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * 基于MybatisPlus请求合并服务类
 *
 * @param <M> BaseMapper子类
 * @param <T> Model子类
 * @author explore
 */
public class QueueServiceImpl<M extends BaseMapper<T>, T extends Model<T>> extends ServiceImpl<M, T> {
    private LinkedBlockingQueue<QueueServiceImpl.ModelFuture<T>> TASK_QUEUE = new LinkedBlockingQueue<>();

    public QueueServiceImpl() {
    }

    /**
     * 从队列中取出指定数量的元素，返回到集合中
     *
     * @param queue 队列实例
     * @param size  指定数量
     * @param <T>   泛型
     * @return 集合实例
     */
    private static <T> List<T> extractElement(Queue<T> queue, int size) {
        Objects.requireNonNull(queue);
        List<T> lists = new ArrayList<>(size);
        for (int i = 0; i < size; ++i) {
            lists.add(queue.poll());
        }
        return lists;
    }

    /**
     * 不推荐重载此方法，推荐重载createRequstConfig方法
     */
    @PostConstruct
    public void init() {
        RequstConfig config = createRequstConfig();
        Runnable runnable = getRunnable(config.getMaxRequestSize());
        BasicThreadFactory threadFactory = new BasicThreadFactory.Builder().namingPattern("scheduled-thread-pool-%d").daemon(true).build();
        ScheduledExecutorService service = new ScheduledThreadPoolExecutor(config.getCorePoolSize(), threadFactory);
        service.scheduleAtFixedRate(runnable, 0L, config.getRequestInterval(), TimeUnit.MILLISECONDS);
    }

    /**
     * 父类调用，子类重载，修改合并请求的行为
     *
     * @return RequstConfig
     */
    protected RequstConfig createRequstConfig() {
        RequstConfig config = new RequstConfig();
        config.setMaxRequestSize(100);
        config.setCorePoolSize(1);
        config.setRequestInterval(200);
        return config;
    }

    /**
     * 构建任务实例
     *
     * @param maxRequestSize 单次最大合并请求数量
     * @return 任务实例
     */
    private Runnable getRunnable(int maxRequestSize) {
        return () -> {
            int size = Math.min(TASK_QUEUE.size(), maxRequestSize);
            if (size != 0) {
                List<ModelFuture<T>> requests = extractElement(TASK_QUEUE, size);
                Set<Serializable> ids = EntityUtils.toSet(requests, ModelFuture::getId);
                List<T> list = super.listByIds(ids);
                Map<Serializable, T> map = EntityUtils.toMap(list, Model::pkVal, e -> e);
                requests.forEach((e) -> e.getFuture().complete(map.get(e.getId())));
            }
        };
    }

    /**
     * 通过主键查询实体
     *
     * @param id 主键ID
     * @return 实体
     */
    @Override
    public T getById(Serializable id) {
        CompletableFuture<T> future = new CompletableFuture<>();
        this.TASK_QUEUE.add(new QueueServiceImpl.ModelFuture<>(id, future));

        try {
            return future.get();
        } catch (ExecutionException | InterruptedException var4) {
            var4.printStackTrace();
            return null;
        }
    }

    /**
     * 包装实体Future类
     *
     * @param <T> 实体
     */
    private static class ModelFuture<T> {
        private Serializable id;
        private CompletableFuture<T> future;

        public ModelFuture(Serializable id, CompletableFuture<T> future) {
            this.id = id;
            this.future = future;
        }

        public Serializable getId() {
            return this.id;
        }

        public void setId(Serializable id) {
            this.id = id;
        }

        public CompletableFuture<T> getFuture() {
            return this.future;
        }

        public void setFuture(CompletableFuture<T> future) {
            this.future = future;
        }
    }

    public static class RequstConfig {
        /* 单次合并最大请求大小 */
        private Integer maxRequestSize;
        /* 线程工厂核心线程数 */
        private Integer corePoolSize;
        /* 合并请求间隔 */
        private Integer requestInterval;

        public Integer getMaxRequestSize() {
            return maxRequestSize;
        }

        public void setMaxRequestSize(Integer maxRequestSize) {
            this.maxRequestSize = maxRequestSize;
        }

        public Integer getCorePoolSize() {
            return corePoolSize;
        }

        public void setCorePoolSize(Integer corePoolSize) {
            this.corePoolSize = corePoolSize;
        }

        public Integer getRequestInterval() {
            return requestInterval;
        }

        public void setRequestInterval(Integer requestInterval) {
            this.requestInterval = requestInterval;
        }
    }
}

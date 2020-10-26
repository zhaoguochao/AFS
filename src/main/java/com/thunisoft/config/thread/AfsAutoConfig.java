package com.thunisoft.config.thread;

import java.util.concurrent.ThreadPoolExecutor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

/**
 * AfsAutoConfig
 *
 * @author zhaoguochao
 * @description AfsAutoConfig
 * @date 2020/9/29 14:52
 */
@Configuration
public class AfsAutoConfig {

    /**
     * 基础线程池，默认使用该线程池
     *
     * @author xingchengmin
     * @date 2019年12月13日 下午5:04:17
     *
     * @return ThreadPoolTaskExecutor
     */
    @Bean("baseThreadPool")
    public ThreadPoolTaskExecutor baseThreadPool() {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        //核心线程数量
        threadPoolTaskExecutor.setCorePoolSize(4);
        //最大线程数量
        threadPoolTaskExecutor.setMaxPoolSize(8);
        //队列中最大任务数
        threadPoolTaskExecutor.setQueueCapacity(1000);
        //线程名称前缀
        threadPoolTaskExecutor.setThreadNamePrefix("AFS-baseThreadPool-");
        //当达到最大线程数时如何处理新任务
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //线程空闲后最大存活时间
        threadPoolTaskExecutor.setKeepAliveSeconds(60);
        threadPoolTaskExecutor.setAllowCoreThreadTimeOut(true);
        //初始化线程池
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }

    /**
     * baseTaskScheduler
     * 并发执行调用任务用
     * @return TaskScheduler
     */
    @Bean(name = "baseTaskScheduler")
    public TaskScheduler baseTaskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(6);
        taskScheduler.setThreadNamePrefix("AFS-Scheduled-");
        taskScheduler.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //调度器shutdown被调用时等待当前被调度的任务完成
        taskScheduler.setWaitForTasksToCompleteOnShutdown(true);
        //等待时长
        taskScheduler.setAwaitTerminationSeconds(60);
        return taskScheduler;
    }
}

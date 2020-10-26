package com.thunisoft.config.thread;

import java.time.LocalDateTime;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import lombok.extern.slf4j.Slf4j;

/**
 * ThreadWork
 *
 * @author zhaoguochao
 * @description ThreadWork
 * @date 2020/10/9 13:43
 */
@Slf4j
@Configuration
public class ThreadWork {
    /**
     * 线程池
     */
    @Resource(name = "baseThreadPool")
    ThreadPoolTaskExecutor baseThreadPool;

    /**
     * 线程共享变量
     */
    private static int SHARED_VARIABLES;

    /**
     * 测试线程1
     */
    public void thread1(){
        baseThreadPool.execute(() -> {
            log.info("当前时间:{}, 线程---thread1", LocalDateTime.now());
            for (int i=0; i< 100 ;i++){
                SHARED_VARIABLES++;
                log.info("当前线程为线程1---变量值为：{}", SHARED_VARIABLES);
            }
        });
    }

    /**
     * 测试线程2
     */
    public void thread2(){
        baseThreadPool.execute(() -> {
            log.info("当前时间:{}, 线程---thread2", LocalDateTime.now());
            for (int i=0; i< 100 ;i++){
                SHARED_VARIABLES++;
                log.info("当前线程为线程2---变量值为：{}", SHARED_VARIABLES);
            }
        });
    }

}

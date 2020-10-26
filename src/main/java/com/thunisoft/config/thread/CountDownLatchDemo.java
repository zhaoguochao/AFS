package com.thunisoft.config.thread;

import java.time.LocalDateTime;
import java.util.concurrent.CountDownLatch;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import lombok.extern.slf4j.Slf4j;

/**
 * CountDownLatchDemo
 *
 * @author zhaoguochao
 * @description 线程测试包
 * @date 2020/9/29 14:49
 */
@Configuration
@Slf4j
public class CountDownLatchDemo {

    /**
     * 线程池
     */
    @Resource(name = "baseThreadPool")
    ThreadPoolTaskExecutor baseThreadPool;

    /**
     * 使用CountDownLatch在线程中计算使用时间
     * @throws InterruptedException
     */
    public long runThread() throws InterruptedException {
        long start = System.currentTimeMillis();
        int totalThread = 3;
        CountDownLatch countDown = new CountDownLatch(totalThread);
        for(int i = 0; i < totalThread; i++) {
            final String threadName = "Thread " + i;
            baseThreadPool.execute(() -> {
                log.info("当前时间:{}, 线程为:{}, started", LocalDateTime.now(), threadName);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    log.error("线程休眠出错", e);
                }
                log.info("当前时间:{}, 线程为:{}, ended", LocalDateTime.now(), threadName);
                countDown.countDown();
            });
        }
        countDown.await();

        long stop = System.currentTimeMillis();
        log.info("线程完成总花费时间:{}ms", stop - start);
        return stop - start;
    }
}

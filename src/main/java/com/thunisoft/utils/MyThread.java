package com.thunisoft.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * MyThread
 *
 * @author zhaoguochao
 * @description 线程测试
 * @date 2020/9/24 9:51
 */
@Slf4j
public class MyThread extends Thread {

    @Override
    public void run() {
        log.info("MyThread线程开始运行");
    }

}

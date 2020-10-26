package com.thunisoft;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.*;

import com.thunisoft.utils.MyThread;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhaoguochao
 */
@MapperScan({ "com.thunisoft.mapper" })
@SpringBootApplication
@EnableEurekaClient
@Slf4j
public class AfsApplication {
	@GetMapping("/")
	String home() {
		return " Welcome To AFS ";
	}

	public static void main(String[] args) {
		SpringApplication.run(AfsApplication.class, args);
		runThread();
	}

	/**
	 * 测试线程
	 */
	synchronized public static void runThread(){
		MyThread myThread = new MyThread();
		myThread.start();
	}

}
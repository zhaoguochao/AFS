package com.thunisoft.controller;


import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;

import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.thunisoft.config.jwt.JwtUtil;
import com.thunisoft.config.jwt.JwtConfigProperties;
import com.thunisoft.config.thread.CountDownLatchDemo;
import com.thunisoft.config.thread.ThreadWork;
import com.thunisoft.model.Afs;
import com.thunisoft.model.ResponseData;
import com.thunisoft.service.UserService;
import com.thunisoft.utils.RedisUtil;

import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;

/**
 * AfsController
 *
 * @author zhaoguochao
 * @description AFS测试类
 * @date 2020/9/7 11:09
 */
@RequestMapping("/afs")
@RestController
@Slf4j
public class AfsController {
    @Value("${server.port}")
    String port;

    /**
     * afsService
     */
    @Resource
    private UserService userService;

    /**
     * jwtConfigProperties
     */
    @Resource
    private JwtConfigProperties jwtConfigProperties;

    /**
     * countDownLatchDemo
     */
    @Resource
    private CountDownLatchDemo countDownLatchDemo;

    /**
     * threadWork
     */
    @Resource
    private ThreadWork threadWork;

    /**
     * redisUtil
     */
    @Resource
    private RedisUtil redisUtil;


    /**
     * 获取信息
     *
     * @return ResponseEntity
     */
    @PostMapping("/getMessage")
    public ResponseEntity getMessage(){
        List<Afs> list =  userService.getMessage();
        log.info("前台请求后台数据接口成功,返回的数据为:{}",list);
        return ResponseEntity.ok().body(new ResponseData(true,"按钮请求成功",list));
    }

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return ResponseEntity
     */
    @PostMapping("/user/login")
    @ResponseBody
    public ResponseEntity login(@RequestParam("username") String username, @RequestParam("password") String password){
        Afs afs = userService.login(username, password);
        if (Objects.isNull(afs)){
            log.error("用户登录失败,username:{},password：{}", username, password);
            return ResponseEntity.ok(new ResponseData(false,"登录失败，您输入的用户名或密码错误!",null));
        }
        //生成token
        String token = JwtUtil.sign(username, jwtConfigProperties);

        //解析token
        String payload = JwtUtil.getPayload(token);
        String header = JwtUtil.getHeader(token);
        log.info("登录成功，返回token为:{}, 测试解析的header为:{}, 测试解析的payload为:{}", token, header, payload);
        return ResponseEntity.ok(new ResponseData(true,"登录成功", token));
    }

    /**
     * 未登录，没有token会重定向到这个方法来
     * @param msg msg
     * @return ResponseEntity
     */
    @GetMapping("/user/unlogin")
    public ResponseEntity unlogin(String msg) {
        log.info("当前请求尚未登录，请求重定向到登录页面");
        msg = new String(msg.getBytes(), StandardCharsets.UTF_8);
        return ResponseEntity.ok(new ResponseData(false, msg));
    }

    /**
     * 获取所有用户，返回json数据，访问时需要在header中带上token
     * @return ResponseEntity
     */
    @RequestMapping("/user/get/all")
    public ResponseEntity getAllUsers(){
        List<Afs> allUsers = userService.getMessage();
        boolean success = !Objects.isNull(allUsers);
        String msg = Objects.isNull(allUsers) ? "请求失败，获取所有用户信息失败" : "请求成功，获取所有用户信息成功";
        return ResponseEntity.ok(new ResponseData(success, msg, allUsers));
    }

    /**
     * 测试Eurka
     * @param name
     * @return String
     */
    @GetMapping("/getport")
    public String getPort(@RequestParam String name){
        return "hi" + name + "I am from Port:" + port;
    }

    /**
     * 测试countDownLatch
     * @return ResponseEntity
     * @throws InterruptedException
     */
    @PostMapping("/testThread")
    public ResponseEntity testThread() throws InterruptedException {
        long completionTime= countDownLatchDemo.runThread();
        return ResponseEntity.ok(new ResponseData(true,"计时完成，时间为:" + completionTime + "ms"));
    }

    /**
     * 测试线程
     * @return ResponseEntity
     */
    @PostMapping("/threadWork")
    @Synchronized
    public ResponseEntity threadWork111() {
        threadWork.thread1();
        threadWork.thread2();
        return ResponseEntity.ok(new ResponseData(true,"并发测试成功"));
    }

    /**
     * 测试redis
     * @return ResponseEntity
     */
    @PostMapping("/setRedis")
    @ResponseBody
    public ResponseEntity setRedis(@RequestBody JSONObject param) {
        String key = param.getString("key");
        String value;
        if(redisUtil.hasKey(key)){
            value = redisUtil.get(key).toString();
            log.info("获取redis缓存中的值, key为:{}, value为:{}", key, value) ;
        }else{
            value = "1022";
            redisUtil.set(key, value);
            log.info("获取库中的值, 并将对应的key和value存入redis中, key:{}, value:{}", key, value);
        }
        return ResponseEntity.ok(new ResponseData(true,"redis存储成功"));
    }
}

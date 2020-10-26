package com.thunisoft.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.thunisoft.mapper.AfsMapper;
import com.thunisoft.model.Afs;

/**
 * UserService
 *
 * @author zhaoguochao
 * @description
 * @date 2020/9/8 17:33
 */
@Service
public class UserService {

    /**
     * afsMapper
     */
    @Resource
    private AfsMapper afsMapper;

    /**
     * 校验登录用户
     *
     * @param username 用户名
     * @param password 密码
     * @return Afs
     */
    public Afs login(String username, String password) {
        return afsMapper.login(username, password);
    }

    /**
     * 获取信息
     * @return List<Afs>
     */
    public List<Afs> getMessage() {
        return afsMapper.getMessage();
    }
}

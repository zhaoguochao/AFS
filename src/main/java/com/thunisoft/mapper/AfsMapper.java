package com.thunisoft.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.thunisoft.model.Afs;

/**
 * AfsMapper
 *
 * @author zhaoguochao
 * @description mapper接口测试类
 * @date 2020/9/7 14:33
 */
@Mapper
public interface AfsMapper {

    /**
     * 获取Afs测试信息
     * @return List<Afs>
     */
    List<Afs> getMessage();

    /**
     * 校验登录
     *
     * @param username 用户名
     * @param password 密码
     * @return Afs
     */
    Afs login(@Param("username") String username, @Param("password") String password);
}

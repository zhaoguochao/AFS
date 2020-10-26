package com.thunisoft.config.jwt;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * JwtConfigProperties
 *
 * @author zhaoguochao
 * @description 拦截器配置类
 * @date 2020/9/8 17:17
 */
@Component
@ConfigurationProperties(prefix = "jwt")
@Data
public class JwtConfigProperties {
    /**
     * expire
     */
    private long expire;

    /**
     * secret
     */
    private String secret;

    public JwtConfigProperties() {
    }

    /**
     * JwtConfigProperties
     * @param expire 过期时间
     * @param secret 密匙
     */
    public JwtConfigProperties(long expire, String secret) {
        this.expire = expire;
        this.secret = secret;
    }
}

package com.thunisoft.config.jwt;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/**
 * WebConfig
 *
 * @author zhaoguochao
 * @description
 * @date 2020/9/8 17:23
 */
@Configuration
public class WebConfig implements WebMvcConfigurer{

    /**
     * 配置跨域请求
     * @param registry registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedHeaders("*")
                .allowedMethods("*");
    }

    /**
     * 设置拦截和放行请求
     *
     * @param registry registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(new JwtInterceptor());
        // 需拦截的路径
        interceptorRegistration.addPathPatterns("/**");
        // 需放行的路径
        interceptorRegistration.excludePathPatterns("/**/login", "/**/unlogin");
    }
}

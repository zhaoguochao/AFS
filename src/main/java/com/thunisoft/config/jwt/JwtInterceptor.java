package com.thunisoft.config.jwt;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;

import lombok.extern.slf4j.Slf4j;

/**
 * JwtInterceptor
 *
 * @author zhaoguochao
 * @description JWT拦截器
 * @date 2020/9/8 17:21
 */
@Slf4j
public class JwtInterceptor extends HandlerInterceptorAdapter {

    /**
     * SpringBoot处理器拦截进行token校验
     *
     * @param request request
     * @param response response
     * @param handler handler
     * @return boolean
     * @throws Exception Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //校验token
        boolean verify = false;
        String errMsg = StringUtils.EMPTY;
        try {
            String token = request.getHeader("Authorization");
            verify = JwtUtil.verify(token);
        } catch (Exception e) {
            errMsg = e.getMessage();
            log.error("token验证失败", e);
        }

        if (!verify){
            errMsg = URLEncoder.encode(errMsg, "UTF-8");
            //这里是个坑，在重定向这里需要设置跨域，不然vue前端会报跨域问题
            response.setHeader("Access-Control-Allow-Origin", "*");
            //重定向到未登录提示页面
            response.sendRedirect("/afs/user/unlogin?msg=" + errMsg);
            return false;
        }
        return true;
    }
}

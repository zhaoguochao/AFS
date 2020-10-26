package com.thunisoft.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * JWTUtil
 *
 * @author zhaoguochao
 * @description API调用工具类
 * @date 2020/9/8 17:18
 */
@Slf4j
@Component
public class JwtUtil {

    /**
     * 配置的密匙
     */
    private static String secret;

    /**
     * 这里@Value无法注入静态变量中，加个set
     * @param secret 密匙
     */
    @Value("${jwt.secret}")
    private void setSecret(String secret) {
        JwtUtil.secret = secret;
    }

    /**
     * 未登录
     */
    private static final String NOT_LOGIN = "未登录";

    /**
     * token已过期
     */
    private static final String EXPIRED = "token已过期";

    /**
     * token签名失败
     */
    private static final String SIGNATURE_VERIFICATION = "token签名失败";

    /**
     * token解析失败，请重新登录获取token
     */
    private static final String DECODE_ERROR = "token解析失败，请重新登录获取token";

    /**
     * 校验token是否正确
     *
     * @param token 密钥
     * @return token是否正确
     */
    public static boolean verify(String token) throws Exception {
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            log.info("jwt-token校验通过!");
            return true;
        } catch (TokenExpiredException e){
            log.info("token验证失败,当前token已过期", e);
            throw new Exception(EXPIRED);
        } catch (SignatureVerificationException e){
            log.info("token验证失败,当前token签名失败", e);
            throw new Exception(SIGNATURE_VERIFICATION);
        } catch (JWTDecodeException e){
            log.info("token解析失败，请重新登录获取token", e);
            throw new Exception(DECODE_ERROR);
        } catch (Exception e) {
            log.error("token为空，请登录系统获取token", e);
            throw new Exception(NOT_LOGIN);
        }
    }

    /**
     * 生成签名,设置过期时间
     * @param username 用户名
     * @return 加密的token
     */
    public static String sign(String username, JwtConfigProperties jwtConfigProperties) {
        Date date = new Date(System.currentTimeMillis()+jwtConfigProperties.getExpire());
        Algorithm algorithm = Algorithm.HMAC256(jwtConfigProperties.getSecret());
        // 构造头部信息
        Map<String, Object> header = new HashMap<>(0);
        header.put("typ","JWT");
        header.put("alg","HS256");

        //构造自定义的payload
        Map<String, Object> myPayload = new HashMap<>(0);
        myPayload.put("name",username);
        myPayload.put("phone","18040104513");
        myPayload.put("address","银河系");
        myPayload.put("sex","男");

        return JWT.create().withHeader(header).withClaim("myPayload", myPayload).withExpiresAt(date).sign(algorithm);
    }


    /**
     * 获得token中的padload信息无需secret解密也能获得
     * 这里可以看到我们可以拿到token的header和payload是可以直接明文解析出来的，但是签名不行，因为签名你没有密匙，而且也不知道是用什么加密方式
     * @return String
     */
    public static String getPayload(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return decode(jwt.getPayload());
        } catch (JWTDecodeException e) {
            log.error("获取payload失败", e);
            return StringUtils.EMPTY;
        }
    }

    /**
     * 获得token中的Header信息无需secret解密也能获得
     * @return String
     */
    public static String getHeader(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return decode(jwt.getHeader());
        } catch (JWTDecodeException e) {
            log.error("获取payload失败", e);
            return StringUtils.EMPTY;
        }
    }

    /**
     * base64解密
     * @param str str
     * @return String
     */
    public static String decode(String str) {
        byte[] data = Base64.getDecoder().decode(str);
        return new String(data, StandardCharsets.UTF_8);
    }
}
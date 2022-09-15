package com.gzzn.service.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;

/**
 * @author eric
 * @date 2022/9/14 15:48
 **/
public class JWTUtil {
    //有效期为30分钟
    public static final long EXPIRE = 1000*60*30;
    //public static final long EXPIRE = 1000*6;
    public static final String SECRET = "abcdefgHIJKLMN";

    /*创建token*/
    public static String createToken(Object data){
        return Jwts.builder()
                //JWT头信息
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS2256")
                //设置分类；设置过期时间 一个当前时间，一个加上设置的过期时间常量
                .setSubject("lin-user")
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                //设置token主体信息，存储用户信息
                .claim("data", data)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    /*校验token*/
    public static void verifyToken(String token) throws Exception{
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
        }catch (Exception e){
            throw e;
        }
    }

    /*解析token*/
    public static Object parseToken(String token){
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
        Claims body = claimsJws.getBody();
        return body.get("data");
    }

    public static void main(String[] args) throws InterruptedException {
        String token = JWTUtil.createToken("12345678");
        Thread.sleep(3000);
        try {
            JWTUtil.verifyToken(token);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(JWTUtil.parseToken(token));
    }
}

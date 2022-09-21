package com.gzzn.service.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @author eric
 * @date 2022/9/14 15:48
 **/
public class JWTUtil {
    //有效期为30分钟

    //public static final long EXPIRE = 1000*60*30;
    public static final long EXPIRE = 1000*3;
    public static final String SECRET = "abcdefgHIJKLMN";

    /*创建token*/

    public static String createToken(Object data){
        long currentTime = System.currentTimeMillis();
        long expireTime = currentTime + EXPIRE;
        return Jwts.builder()
                //JWT头信息
                .setHeaderParam("typ", "JWT")
                .setHeaderParam("alg", "HS2256")
                //设置分类；设置过期时间 一个当前时间，一个加上设置的过期时间常量
                .setSubject("lin-user")
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(expireTime))
                //设置token主体信息，存储用户信息
                .claim("data", data)
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    /*解析token*/
    public static <T> T parseToken(String token) throws Exception{
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
        Claims body = claimsJws.getBody();
        return (T)body.get("data");
    }

    public static void main(String[] args) throws InterruptedException {
        String token = JWTUtil.createToken("12345678");
        System.out.println(token);
        Thread.sleep(500);
        try {
            String value  =  JWTUtil.parseToken(token);
            System.out.println(value);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

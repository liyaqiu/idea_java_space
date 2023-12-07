package com.acl.security2.utils;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTHeader;
import cn.hutool.jwt.JWTUtil;

import java.util.HashMap;
import java.util.Map;

public class CJWTUtil {

    private static final String salt = "qwqweqweqweqeqeew";
    //创建
    public static String createToken(String username) {
        Map<String, Object> map = new HashMap<String, Object>() {
            private static final long serialVersionUID = 1L;
            {
                put("uid", Integer.parseInt("123"));
                put("username", username);
                put("expire_time", System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 15);
            }
        };
        String token = JWTUtil.createToken(map, salt.getBytes());
        return token;
    }

    //JWT解析
    public static String parseToken(String token) {
        JWT jwt = JWTUtil.parseToken(token);
        jwt.getHeader(JWTHeader.TYPE);
        return (String) jwt.getPayload("username");
    }

    //JWT验证
    public static boolean verifyToken(String token) {
        return JWTUtil.verify(token, salt.getBytes());
    }
}

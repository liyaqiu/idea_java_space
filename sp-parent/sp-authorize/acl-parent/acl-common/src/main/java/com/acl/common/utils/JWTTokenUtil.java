package com.acl.common.utils;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTPayload;
import cn.hutool.jwt.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author eric
 * @date 2022/3/23 17:22
 **/
@Slf4j
public class JWTTokenUtil {
    //超时时间为1小时
    final static long expire =  1000 * 60 * 60;
    final static String salt = "eric";

    private JWTTokenUtil(){};

    /**
     * token生成
     * */
    public static String tokenGener(String str){
        Map<String, Object> map = new HashMap<String, Object>() {
            private static final long serialVersionUID = 1L;
            {
                put("token", str);
                put("expire_time", System.currentTimeMillis() + expire);
            }
        };
        return JWTUtil.createToken(map, salt.getBytes());
    }

    /**
     * token校验
     * */
    public static boolean tokenVerify(String token){
        return JWTUtil.verify(token, salt.getBytes(StandardCharsets.UTF_8));
    }

    /**
     * token解析
     * */
    public static String tokenParse(String token){
        try {
            JWT jwt = JWTUtil.parseToken(token);
            JWTPayload payload = jwt.getPayload();
            //判断有效期
            if(System.currentTimeMillis()<(Long) payload.getClaim("expire_time")){
                return (String)payload.getClaim("token");
            }
        }catch (Exception e){
            log.debug("jwt解析异常",e);
        }
       return null;
    }


    public static void main(String[] args) throws InterruptedException {
        JWTTokenUtil tokenUtil = new JWTTokenUtil();
        String token = tokenUtil.tokenGener("user_liyaqiu");
        System.out.println(token);
        //Thread.sleep(1200);
        System.out.println(tokenUtil.tokenParse("123"));

    }
}

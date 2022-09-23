package com.acl.secure.handler;

import com.acl.common.utils.JWTTokenUtil;
import com.acl.common.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author eric
 * @date 2022/3/23 18:25
 * 退出处理器
 **/
@Slf4j
public class MyLogoutHandler implements LogoutHandler {

    StringRedisTemplate redisTemplate;

    public MyLogoutHandler(StringRedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {


        try {
            log.info("退出登录");
            String token = request.getHeader("token");
            if(token!=null){
                String userName = JWTTokenUtil.tokenParse(token);
                if(userName!=null){
                    //从redis上删除key
                    redisTemplate.delete(userName);
                    response.setContentType("text/html;charset=utf-8");
                    response.getWriter().write(ResultUtil.okJson());
                    response.flushBuffer();
                }else{
                    response.setContentType("text/html;charset=utf-8");
                    response.getWriter().write(ResultUtil.errJson());
                    response.flushBuffer();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

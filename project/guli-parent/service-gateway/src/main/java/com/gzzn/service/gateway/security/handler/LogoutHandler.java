package com.gzzn.service.gateway.security.handler;

import com.gzzn.service.gateway.security.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.logout.ServerLogoutHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author eric
 * @date 2022/9/29 16:55
 **/
@Component
@Slf4j
public class LogoutHandler implements ServerLogoutHandler {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public Mono<Void> logout(WebFilterExchange exchange, Authentication authentication) {
        log.debug("logout");
        //如果传递token就会获得用户名，如果不传递则是匿名
        if(authentication.getPrincipal() instanceof UserModel){
            UserModel userModel = ((UserModel) authentication.getPrincipal());
            redisTemplate.delete(userModel.getUsername());
        }
        return Mono.empty();
    }
}

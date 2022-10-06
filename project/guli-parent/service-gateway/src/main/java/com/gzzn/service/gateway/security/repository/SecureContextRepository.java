package com.gzzn.service.gateway.security.repository;

import com.google.gson.Gson;
import com.gzzn.service.gateway.security.model.UserModel;
import com.gzzn.service.gateway.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * @author eric
 * @date 2022/9/27 18:20
 **/
@Component
@Slf4j
public class SecureContextRepository implements ServerSecurityContextRepository {
    @Autowired
    StringRedisTemplate redisTemplate;
    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        log.debug("MyContextRepository save");
        //将登陆的数据保存到redis
        //只有登录的时候会调用此方法
        UserModel userModel = (UserModel) context.getAuthentication().getPrincipal();
        userModel.setPassword(null);
        //存入权限列表信息到redis
        redisTemplate.opsForValue().set(userModel.getUsername(), new Gson().toJson(userModel));
        return Mono.empty();
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        //访问登录URL地址的时候会调用此方法，以及save方法
        //访问退出登录URL和其他API请求也会调用此方法，不会调用save方法
        log.debug("MyContextRepository load");

        HttpHeaders headers = exchange.getRequest().getHeaders();

        String username = headers.getFirst("username");

        /**
         * username为什么为空？此方法除了登录接口会调用，其他api接口访问的时候也会进行调用
         * 如果返回空会走NoLoginHander未登录的处理逻辑
         * */
        if(username==null){
            return Mono.empty();
        }

        String userInfo = redisTemplate.opsForValue().get(username);

        /**
         *因为颁发的token是jwt的，所以令牌有可能还没过期，但用户是可以退出登录了，依靠这个key来做判断
         *如果登陆过，那么肯定key存在，不会返回null，如果key不存在，肯定没登陆过
         */
        if(userInfo ==null){
            log.debug("该用户已经退出登录了");
            return Mono.empty();
        }
        UserModel userModel = new Gson().fromJson(userInfo, UserModel.class);
        log.debug("从redis获得的用户信息 {}",userModel);

        SecurityContext securityContext = new SecurityContextImpl();
        securityContext.setAuthentication(new UsernamePasswordAuthenticationToken(userModel,null,userModel.getAuthorities()));

        return Mono.just(securityContext);
    }
}

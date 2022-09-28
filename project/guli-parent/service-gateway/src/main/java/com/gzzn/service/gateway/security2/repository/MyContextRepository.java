package com.gzzn.service.gateway.security2.repository;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.google.gson.Gson;
import com.gzzn.service.gateway.security2.model.UserModel;
import com.gzzn.service.gateway.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author eric
 * @date 2022/9/27 18:20
 **/
@Component
@Slf4j
public class MyContextRepository implements ServerSecurityContextRepository {
    @Autowired
    StringRedisTemplate redisTemplate;
    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        System.out.println(context.getAuthentication().getClass());
        //只有登录的时候会调用此方法
        UserModel userModel = (UserModel) context.getAuthentication().getPrincipal();
        log.debug("MyContextRepository save {}",userModel);
        //存入权限列表信息到redis
        redisTemplate.opsForValue().set(userModel.getUsername(), new Gson().toJson(userModel.getAuthorityList()));
        return Mono.empty();
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        //访问登录URL地址的时候会调用此方法，以及save方法
        //后面如果是请求api了，只调用此方法，不会调用save方法
        log.debug("MyContextRepository load");

        HttpHeaders headers = exchange.getRequest().getHeaders();
        //前面token过滤器已经做了校验，所以这里不需要再次校验
        String token = headers.getFirst("token");

        if(token!=null){
            String userName = null;
            try {
                //解析token获得用户名
                userName = JWTUtil.parseToken(token);
                String authorities = redisTemplate.opsForValue().get(userName);

                List<String> authorityList = new Gson().fromJson(authorities, ArrayList.class);
                UserModel userModel = new UserModel(userName,authorityList);
                System.out.println(userModel);

                SecurityContext securityContext = new SecurityContextImpl();
                securityContext.setAuthentication(new UsernamePasswordAuthenticationToken(userModel,null,userModel.getAuthorities()));
                return Mono.just(securityContext);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Mono.empty();
    }
}

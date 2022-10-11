package com.gzzn.service.gateway.security.service;

import cn.hutool.json.JSONUtil;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.gzzn.service.gateway.client.ServiceAclClient;
import com.gzzn.service.gateway.security.model.UserModel;
import com.gzzn.service.utils.Res;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * @author eric
 * @date 2022/9/25 17:18
 **/
@Component
@Slf4j
public class UserDetailService implements ReactiveUserDetailsService {

    /*
    https://blog.csdn.net/qq_43410878/article/details/123825870
    循环依赖引发的问题
    使用 @Lazy 做延迟加载
    */
    @Autowired
    @Lazy
    ServiceAclClient serviceAclClient;

    /**
     * 只有登陆的接口才触发此方法，加载用户信息以及权限信息一并返回
     * */
    @Override
    public Mono<UserDetails> findByUsername(String username) {

        log.debug("findByUsername {}",username);
        Res res = serviceAclClient.queryAuthoritiesByUsername(username);
        log.debug(new Gson().toJson(res));
        /**
         * res == null 熔断
         * res.getData() == null 没查询到
         * */
        if(res!=null && res.getData()!=null){
            UserModel userModel = new Gson().fromJson(String.valueOf(res.getData()), UserModel.class);
            log.debug("从数据库获得的用户信息 {}",userModel);
            return Mono.just(userModel);

            /*String userName = "liyaqiu";
            String password = "$2a$10$7WdbN5Bw7H0CwWXTv97fyenS4w/rwGTrG2YvW6tT0oXGLmPe2vHCK";
            List<String> authorities = new ArrayList<>();
            authorities.add("subject:list");
            authorities.add("teacher:list");
            authorities.add("teacher:get");
            authorities.add("teacher:delete");
            return Mono.just(new UserModel(userName,password,authorities));*/
        }
        //如果用户名不存在，则返回空，那么就会走失败处理的LoginFailureHandler
        return Mono.empty();
    }
}

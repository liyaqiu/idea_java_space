package com.gzzn.service.gateway.security.service;

import com.gzzn.service.gateway.security.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

/**
 * @author eric
 * @date 2022/9/25 17:18
 **/
@Service
@Slf4j
public class UserDetailService implements ReactiveUserDetailsService {
    /*@Autowired
    UserMapper userMapper;*/

    /**
     * 只有登陆的接口才触发此方法，加载用户信息以及权限信息一并返回
     * */
    @Override
    public Mono<UserDetails> findByUsername(String username) {
        log.debug("findByUsername {}",username);
        if(true){
            String userName = "liyaqiu";
            String password = "$2a$10$7WdbN5Bw7H0CwWXTv97fyenS4w/rwGTrG2YvW6tT0oXGLmPe2vHCK";
            List<String> authorities = new ArrayList<>();
            authorities.add("subject:list");
            authorities.add("teacher:list");
            authorities.add("teacher:get");
            authorities.add("teacher:delete");
            return Mono.just(new UserModel(userName,password,authorities));
        }
        //如果用户名不存在，则返回空，那么就会走失败处理的LoginFailureHandler
        return Mono.empty();
    }
}

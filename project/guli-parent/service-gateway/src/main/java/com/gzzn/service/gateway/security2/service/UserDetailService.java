package com.gzzn.service.gateway.security2.service;

import com.gzzn.service.gateway.security2.model.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
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

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        log.debug("findByUsername {}",username);

        String userName = "liyaqiu";
        String password = "$2a$10$7WdbN5Bw7H0CwWXTv97fyenS4w/rwGTrG2YvW6tT0oXGLmPe2vHCK";
        List<GrantedAuthority> authorities = new ArrayList<>();
               /* list.add(()->"subject:list");
                list.add(()->"teacher:list");*/
        authorities.add(()->"teacher:get");
        authorities.add(()->"teacher:delete");
        return Mono.just(new UserModel(userName,password,authorities));
    }
}

package com.gzzn.service.gateway.security2.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Collection;

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
        UserDetails userDetails = new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public String getPassword() {
                return "123456";
            }

            @Override
            public String getUsername() {
                return "liyaqiu";
            }

            @Override
            public boolean isAccountNonExpired() {
                return false;
            }

            @Override
            public boolean isAccountNonLocked() {
                return false;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return false;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };
        return Mono.just(userDetails);
    }


}

package com.gzzn.service.gateway.security.impl;


import com.gzzn.service.gateway.entity.AclUserEntity;
import com.gzzn.service.gateway.security.model.SecureUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Collection;

/**
 * https://blog.csdn.net/qq_43692950/article/details/122513687
 * @author eric
 * @date 2022/9/21 19:29
 **/
//@Service
@Slf4j
public class UserDetailServieImpl implements ReactiveUserDetailsService {
    /*@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new SecureUser(new AclUserEntity());
    }*/

    @Override
    public Mono<UserDetails> findByUsername(String username) {

        log.debug("findByUsername");
        UserDetails userDetails = new UserDetails() {

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return null;
            }

            @Override
            public String getPassword() {
                return null;
            }

            @Override
            public String getUsername() {
                return null;
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
                return false;
            }
        };
        return Mono.just(userDetails);
    }

}

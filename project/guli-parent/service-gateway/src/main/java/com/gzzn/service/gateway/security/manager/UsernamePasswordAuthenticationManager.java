package com.gzzn.service.gateway.security.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collection;

/**
 * @author eric
 * @date 2022/9/25 12:02
 **/

@Slf4j
//@Component
public class UsernamePasswordAuthenticationManager implements ReactiveAuthenticationManager {
    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        log.info("authenticate");
        return Mono.just(new MyAuthentication());
    }
}
@Slf4j
class MyAuthentication implements Authentication{

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        log.debug("GrantedAuthority");
        return null;
    }

    @Override
    public Object getCredentials() {
        log.debug("getCredentials");
        return null;
    }

    @Override
    public Object getDetails() {
        log.debug("getDetails");
        return null;
    }

    @Override
    public Object getPrincipal() {
        log.debug("getPrincipal");
        return null;
    }

    @Override
    public boolean isAuthenticated() {
        log.debug("isAuthenticated");
        return true;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        log.debug("setAuthenticated");
    }

    @Override
    public String getName() {
        log.debug("getName");
        return "liyaqiu";
    }
}

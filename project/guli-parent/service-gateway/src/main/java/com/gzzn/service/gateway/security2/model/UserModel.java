package com.gzzn.service.gateway.security2.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author eric
 * @date 2022/9/27 19:10
 **/
@Slf4j
public class UserModel implements UserDetails {

    String userName;
    String password;
    List<GrantedAuthority> list;

    public UserModel(String userName, String password, List<GrantedAuthority> list) {
        this.userName = userName;
        this.password = password;
        this.list = list;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        log.debug("getAuthorities");
        return this.list;
    }

    @Override
    public String getPassword() {
        log.debug("getPassword");
        return this.password;
    }

    @Override
    public String getUsername() {
        log.debug("getUsername");
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        log.debug("isAccountNonExpired");
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        log.debug("isAccountNonLocked");
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        log.debug("isCredentialsNonExpired");
        return true;
    }

    @Override
    public boolean isEnabled() {
        log.debug("isEnabled");
        return true;
    }
}

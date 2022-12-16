package com.gzzn.service.gateway.security.model;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

/**
 * @author eric
 * @date 2022/9/27 19:10
 **/
@Slf4j
public class UserModel implements UserDetails {

    private String id;
    private String username;
    private String password;
    private Boolean locked; //true为锁定，false为不锁定
    private List<String> permits;


    public UserModel() {
    }

    public UserModel(String username, List<String> authorityList) {
        this.username = username;
        this.permits = authorityList;
    }

    public UserModel(String username, String password, List<String> authorities) {
        this.username = username;
        this.password = password;
        this.permits = authorities;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Boolean getLocked() {
        return locked;
    }

    public void setLocked(Boolean locked) {
        this.locked = locked;
    }

    public List<String> getPermits() {
        return permits;
    }

    public void setPermits(List<String> permits) {
        this.permits = permits;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getAuthorityList() {
        return permits;
    }

    /**
     * 将关于这个用户的权限返回给spring security框架
     * */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //log.debug("getAuthorities");
        List<GrantedAuthority> ga = new ArrayList<>();
        permits.stream().forEach((permit)-> {
            ga.add(()-> permit);
        });

        return ga;
    }

    @Override
    public String getPassword() {
        //log.debug("getPassword");
        return password;
    }

    @Override
    public String getUsername() {
        //log.debug("getUsername");
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        //log.debug("isAccountNonExpired");
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        log.debug("isAccountNonLocked");
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        //log.debug("isCredentialsNonExpired");
        return true;
    }

    @Override
    public boolean isEnabled() {
        //log.debug("isEnabled");
        return true;
    }

    @Override
    public String toString() {
        return "UserModel{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", locked=" + locked +
                ", permits=" + permits +
                '}';
    }
}

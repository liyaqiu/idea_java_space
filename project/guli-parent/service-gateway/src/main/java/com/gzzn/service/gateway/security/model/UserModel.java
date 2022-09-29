package com.gzzn.service.gateway.security.model;

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

    private String userName;
    private String password;
    private List<String> authorityList;


    public UserModel(String userName, List<String> authorityList) {
        this.userName = userName;
        this.authorityList = authorityList;
    }

    public UserModel(String userName, String password, List<String> authorities) {
        this.userName = userName;
        this.password = password;
        this.authorityList = authorities;
    }

    public List<String> getAuthorityList() {
        return authorityList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //log.debug("getAuthorities");
        List<GrantedAuthority> ga = new ArrayList<>();
        authorityList.stream().forEach((authority)-> {
            ga.add(()-> authority);
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
        return userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        //log.debug("isAccountNonExpired");
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        //log.debug("isAccountNonLocked");
        return true;
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
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", authorityList=" + authorityList +
                '}';
    }
}

package com.acl.security2.model;

import com.gzzn.service.acl.entity.AclUserEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @author eric
 * @date 2022/9/21 19:41
 **/
public class SecureUser implements UserDetails {

    private AclUserEntity aclUserEntity;

    public SecureUser(AclUserEntity aclUserEntity) {
        this.aclUserEntity = aclUserEntity;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return aclUserEntity.getPasswd();
    }

    @Override
    public String getUsername() {
        return aclUserEntity.getUserName();
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
}

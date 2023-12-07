package com.acl.security2.service.impl;


import com.acl.security2.model.AclUserEntity;
import com.acl.security2.model.SecureUser;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author eric
 * @date 2022/9/21 19:29
 **/
@Service
public class UserDetailServieImpl implements UserDetailsService {



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AclUserEntity aclUser = new AclUserEntity();
        return new SecureUser(aclUser);
    }
}

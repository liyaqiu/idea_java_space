package com.acl.security2.service.impl;

import com.gzzn.service.acl.entity.AclUserEntity;
import com.gzzn.service.acl.mapper.AclUserMapper;
import com.gzzn.service.acl.security.model.SecureUser;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    AclUserMapper aclUserMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AclUserEntity aclUser = aclUserMapper.selectById("1");
        return new SecureUser(aclUser);
    }
}

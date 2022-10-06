package com.gzzn.service.acl.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gzzn.service.acl.entity.AclUserEntity;
import com.gzzn.service.acl.mapper.AclUserMapper;
import com.gzzn.service.acl.service.AclUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author lyq
 * @date 2021/12/6 12:43
 */
@Service
@Slf4j
public class AclUserServiceImpl extends ServiceImpl<AclUserMapper, AclUserEntity> implements AclUserService {


    @Override
    public AclUserEntity getUserInfoByUsername(String username) {
        QueryWrapper wrapper  = new QueryWrapper();
        wrapper.eq("username", username);
        AclUserEntity aclUser = baseMapper.selectOne(wrapper);
        aclUser.setPassword(null);
        aclUser.setLocked(null);
        return aclUser;
    }
}

package com.gzzn.service.acl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gzzn.service.acl.entity.AclPermissionEntity;
import com.gzzn.service.acl.entity.AclUserEntity;
import com.gzzn.service.acl.mapper.AclUserMapper;
import com.gzzn.service.acl.service.impl.AclPermissionServiceImpl;
import com.gzzn.service.acl.vo.resp.QueryAuthoritiesByUsernameVo;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lyq
 * @date 2021/12/6 12:39
 */

public interface AclPermissionService extends IService<AclPermissionEntity> {

    QueryAuthoritiesByUsernameVo queryAuthoritiesByUsername(String username);
}
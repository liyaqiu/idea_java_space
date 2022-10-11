package com.gzzn.service.acl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gzzn.service.acl.entity.AclRoleEntity;

import java.util.List;

/**
 * @author lyq
 * @date 2021/12/6 12:39
 */

public interface AclRoleService extends IService<AclRoleEntity> {

    void removeAclRole(String id);

    List<String> queryPermissionIdsByRoleId(String roleId);

    void updatePermissionIdsByRoleId(String roleId,List<String> permitIds);

    List<AclRoleEntity> queryAllRole();

    List<String> queryAllRoleIdsByUserId(String userId);

    void updateRoleIdsByUserId(String userId, List<String> roleIds);
}

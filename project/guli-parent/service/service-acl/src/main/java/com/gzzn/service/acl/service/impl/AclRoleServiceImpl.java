package com.gzzn.service.acl.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gzzn.service.acl.entity.AclRoleEntity;
import com.gzzn.service.acl.entity.AclRolePermissionEntity;
import com.gzzn.service.acl.entity.AclUserRoleEntity;
import com.gzzn.service.acl.mapper.AclRoleMapper;
import com.gzzn.service.acl.mapper.AclRolePermissionMapper;
import com.gzzn.service.acl.mapper.AclUserRoleMapper;
import com.gzzn.service.acl.service.AclRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author lyq
 * @date 2021/12/6 12:43
 */
@Service
@Slf4j
public class AclRoleServiceImpl extends ServiceImpl<AclRoleMapper, AclRoleEntity> implements AclRoleService {

    @Autowired
    AclRoleMapper aclRoleMapper;
    @Autowired
    AclRolePermissionMapper aclRolePermissionMapper;
    @Autowired
    AclUserRoleMapper aclUserRoleMapper;

    @Override
    public void removeAclRole(String id) {
        if (baseMapper.deleteById(id)!=1) {
            throw new RuntimeException("删除失败");
        }
    }

    @Override
    public List<String> queryPermissionIdsByRoleId(String roleId) {
        List<String> permitIds = aclRoleMapper.selectPermissionIdsByRoleId(roleId);
        //过滤掉null值
        permitIds = permitIds.stream().filter(permitId -> permitId != null).collect(Collectors.toList());
        return permitIds;
    }

    @Override
    @Transactional
    public void updatePermissionIdsByRoleId(String roleId, List<String> permitIds) {
        List<String> rolePermissionIds = aclRoleMapper.selectRolePermissionIdsByRoleId(roleId);

        //过滤掉null值
        rolePermissionIds = rolePermissionIds.stream().filter(permitId -> permitId != null).collect(Collectors.toList());

        if(rolePermissionIds.size()>0){
            aclRolePermissionMapper.deleteBatchIds(rolePermissionIds);
        }

        for (String permitId : permitIds) {
            AclRolePermissionEntity aclRolePermission = new AclRolePermissionEntity();
            aclRolePermission.setRoleId(roleId);
            aclRolePermission.setPermissionId(permitId);
            aclRolePermission.setGmtCreate(new Date());
            aclRolePermission.setGmtModified(new Date());
            aclRolePermissionMapper.insert(aclRolePermission);
        }
    }

    @Override
    @Transactional
    public void updateRoleIdsByUserId(String userId, List<String> roleIds) {
        List<String> userRoleIds = aclRoleMapper.selectUserRoleIdsByUserId(userId);

        if(userRoleIds.size()>0){
            aclUserRoleMapper.deleteBatchIds(userRoleIds);
        }

        for (String roleId : roleIds) {
            AclUserRoleEntity aclUserRole = new AclUserRoleEntity();
            aclUserRole.setRoleId(roleId);
            aclUserRole.setUserId(userId);
            aclUserRole.setGmtCreate(new Date());
            aclUserRole.setGmtModified(new Date());
            aclUserRoleMapper.insert(aclUserRole);
        }
    }


    @Override
    public List<AclRoleEntity> queryAllRole() {
        return aclRoleMapper.selectList(null);
    }

    @Override
    public List<String> queryAllRoleIdsByUserId(String userId) {
        List<String> roleIds = aclRoleMapper.selectAllRoleIdsByUserId(userId);
        return roleIds;
    }


}

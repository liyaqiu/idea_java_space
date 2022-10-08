package com.gzzn.service.acl.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gzzn.service.acl.entity.AclPermissionEntity;
import com.gzzn.service.acl.mapper.AclPermissionMapper;
import com.gzzn.service.acl.service.AclPermissionService;
import com.gzzn.service.acl.vo.resp.QueryAuthoritiesByUsernameVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lyq
 * @date 2021/12/6 12:43
 */
@Service
@Slf4j
public class AclPermissionServiceImpl extends ServiceImpl<AclPermissionMapper, AclPermissionEntity> implements AclPermissionService {

    @Autowired
    AclPermissionMapper aclPermissionMapper;

    @Override
    public QueryAuthoritiesByUsernameVo queryAuthoritiesByUsername(String username) {
        List<Map<String,Object>> list = aclPermissionMapper.selectAuthoritiesByUsername(username);

        if(list.size()>0){
            QueryAuthoritiesByUsernameVo vo = new QueryAuthoritiesByUsernameVo();
            Map<String, Object> objectMap = list.get(0);
            vo.setId(String.valueOf(objectMap.get("id")));
            vo.setUsername(String.valueOf(objectMap.get("username")));
            vo.setPassword(String.valueOf(objectMap.get("password")));
            vo.setLocked(Boolean.valueOf(String.valueOf(objectMap.get("locked"))));

            for (Map<String, Object> map : list) {
                if(map.get("permit")!=null){
                    vo.getPermits().add(String.valueOf(map.get("permit")));
                }
            }
            return vo;
        }
        return null;
    }

    @Override
    public List<AclPermissionEntity> queryMenuByUsername(String username) {
        List<AclPermissionEntity> menuList = aclPermissionMapper.selectMenuByUsername(username);
        return menuList;
    }

    @Override
    public List<String> queryFAuthoritiesByUsername(String username) {
        List<String> authorities = aclPermissionMapper.selectFAuthoritiesByUsername(username);
        return authorities;
    }

    @Override
    public void queryAllPermission() {
        List<AclPermissionEntity> permissionList = aclPermissionMapper.selectList(null);
        List<AclPermissionEntity> parentList = buildTree(permissionList);
        System.out.println(JSONObject.toJSON(parentList));
    }

    private List<AclPermissionEntity> buildTree(List<AclPermissionEntity> permissionList){
        List<AclPermissionEntity> parentList = new ArrayList<>();
        for (AclPermissionEntity aclPermission : permissionList) {
            if (aclPermission.getParentId() == null) {
                parentList.add(aclPermission);
            }
        }
        return parentList;
    }

}

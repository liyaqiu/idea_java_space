package com.gzzn.service.acl.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.gzzn.service.acl.entity.AclPermissionEntity;
import com.gzzn.service.acl.mapper.AclPermissionMapper;
import com.gzzn.service.acl.mapper.AclRolePermissionMapper;
import com.gzzn.service.acl.service.AclPermissionService;
import com.gzzn.service.acl.vo.resp.QueryAuthoritiesByUsernameVo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    AclRolePermissionMapper aclRolePermissionMapper;
    @Autowired
    StringRedisTemplate redisTemplate;

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
    public List<AclPermissionEntity> queryAllPermission() {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.orderByAsc("sort");
        List<AclPermissionEntity> permissionList = aclPermissionMapper.selectList(wrapper);
        List<AclPermissionEntity> parentList = buildTree(permissionList);
        return parentList;
    }

    @Override
    public void saveEduTeacher(AclPermissionEntity aclPermission) {
        if(aclPermissionMapper.insert(aclPermission)==0){
            throw new RuntimeException("保存失败");
        }
    }

    @Override
    @Transactional
    public void removeAclPermissionById(String id) {

        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("parent_id", id);
        if(aclPermissionMapper.selectList(wrapper).size()>0){
            throw new RuntimeException("当前权限存在子级，不允许删除");
        }

        //先查后删
        List<String> usernameList = aclPermissionMapper.selectUsernamesByPermissionId(id);

        if (aclPermissionMapper.deleteById(id)!=1) {
            throw new RuntimeException("删除失败");
        }

        //删除中间表关联的权限
        wrapper = new QueryWrapper();
        wrapper.eq("permission_id", id);
        aclRolePermissionMapper.delete(wrapper);

        //判断哪些用户已经存进redis中
        usernameList = usernameList.stream().filter(username -> redisTemplate.opsForValue().get(username) != null).collect(Collectors.toList());

        //对存在redis中的数据做更新
        for (String username : usernameList) {
            QueryAuthoritiesByUsernameVo vo = queryAuthoritiesByUsername(username);
            redisTemplate.opsForValue().set(username, new Gson().toJson(vo));
        }


    }

    private List<AclPermissionEntity> buildTree(List<AclPermissionEntity> permissionList){
        List<AclPermissionEntity> parentList = new ArrayList<>();
        for (AclPermissionEntity aclPermission : permissionList) {
            for (AclPermissionEntity aclPer : permissionList) {
                if(aclPermission.getId().equals(aclPer.getParentId())){
                    aclPermission.getChildren().add(aclPer);
                }
            }
            if (aclPermission.getParentId() == null) {
                parentList.add(aclPermission);
            }
        }
        return parentList;
    }

}

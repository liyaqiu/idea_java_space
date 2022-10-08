package com.gzzn.service.acl.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gzzn.service.acl.entity.AclPermissionEntity;
import com.gzzn.service.acl.entity.AclUserEntity;
import com.gzzn.service.acl.mapper.AclUserMapper;
import com.gzzn.service.acl.service.AclPermissionService;
import com.gzzn.service.acl.service.AclUserService;
import com.gzzn.service.acl.vo.resp.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author lyq
 * @date 2021/12/6 12:43
 */
@Service
@Slf4j
public class AclUserServiceImpl extends ServiceImpl<AclUserMapper, AclUserEntity> implements AclUserService {


    @Autowired
    AclPermissionService aclPermissionService;

    @Override
    public UserInfoVo getUserInfoByUsername(String username) {
        UserInfoVo vo = new UserInfoVo();

        QueryWrapper wrapper  = new QueryWrapper();
        wrapper.eq("username", username);
        AclUserEntity aclUser = baseMapper.selectOne(wrapper);
        aclUser.setPassword(null);
        aclUser.setLocked(null);
        vo.setAclUser(aclUser);

        List<AclPermissionEntity> menuList = aclPermissionService.queryMenuByUsername(username);
        //返回用户菜单信息
        List<AclPermissionEntity> menuTreeList = buildTree(menuList);
        vo.setMenuTreeList(menuTreeList);

        List<String> authorities = aclPermissionService.queryFAuthoritiesByUsername(username);
        vo.setAuthorities(authorities);

        return vo;
    }


    private List<AclPermissionEntity> buildTree(List<AclPermissionEntity> menuList){
        List<AclPermissionEntity> menuTreeList = new ArrayList<>();
        for (AclPermissionEntity p1 : menuList) {
            for (AclPermissionEntity p2 : menuList) {
                if(p1.getId().equals(p2.getParentId())){
                    p1.getChildren().add(p2);
                }
            }
            if(p1.getParentId() == null){
                menuTreeList.add(p1);
            }
        }
        return menuTreeList;
    }
}

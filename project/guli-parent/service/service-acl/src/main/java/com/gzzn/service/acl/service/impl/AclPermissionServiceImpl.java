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

import java.util.List;
import java.util.Map;
import java.util.Set;
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
        QueryAuthoritiesByUsernameVo vo = new QueryAuthoritiesByUsernameVo();

        if(list.size()>0){
            Map<String, Object> objectMap = list.get(0);
            vo.setId(String.valueOf(objectMap.get("id")));
            vo.setUsername(String.valueOf(objectMap.get("username")));
            vo.setPassword(String.valueOf(objectMap.get("password")));
            vo.setLocked(Boolean.valueOf(String.valueOf(objectMap.get("locked"))));
            Set<String> permits = list.stream().map(map -> String.valueOf(map.get("permit"))).collect(Collectors.toSet());
            vo.setPermits(permits);
        }

        return vo;
    }
}

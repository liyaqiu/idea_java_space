package com.gzzn.service.acl.controller;

import com.alibaba.fastjson.JSONObject;
import com.gzzn.service.acl.service.AclPermissionService;
import com.gzzn.service.acl.vo.resp.QueryAuthoritiesByUsernameVo;
import com.gzzn.service.utils.Res;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lyq
 * @date 2021/12/4 12:33
 */
@RestController
@RequestMapping("/acl/permission")
@Slf4j
@Api(tags = {"权限管理"})
public class AclPermissionController {

    @Autowired
    private AclPermissionService aclPermissionService;

    @GetMapping("/{username}")
    public Res queryAuthoritiesByUsername(@PathVariable("username") String username){
        log.info("queryAuthoritiesByUsername {}",username);
        QueryAuthoritiesByUsernameVo vo = aclPermissionService.queryAuthoritiesByUsername(username);
        return Res.ok().setData(vo);
    }


}

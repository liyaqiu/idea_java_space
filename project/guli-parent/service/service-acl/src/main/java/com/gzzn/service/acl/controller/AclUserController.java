package com.gzzn.service.acl.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gzzn.service.acl.entity.AclUserEntity;
import com.gzzn.service.acl.service.AclUserService;
import com.gzzn.service.utils.Res;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lyq
 * @date 2021/12/4 12:33
 */
@RestController
@RequestMapping("/acl/user")
@Slf4j
@Api(tags = {"用户管理"})
public class AclUserController {

    @Autowired
    private AclUserService aclUserService;

    @GetMapping("/info")
    public Res getUserInfoByUsername(@RequestHeader("username") String username){
        AclUserEntity aclUser = aclUserService.getUserInfoByUsername(username);
        return Res.ok().setData(aclUser);
    }


}

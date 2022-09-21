package com.gzzn.service.acl.controller;

import com.gzzn.service.acl.service.AclUserService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping
    public void test(){
        log.info("{}","test");
    }


}

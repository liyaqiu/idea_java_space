package com.gzzn.service.acl.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gzzn.service.acl.entity.AclUserEntity;
import com.gzzn.service.acl.service.AclUserService;
import com.gzzn.service.acl.vo.resp.UserInfoVo;
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
        UserInfoVo vo = aclUserService.getUserInfoByUsername(username);

        log.debug("返回用户菜单信息 {}", JSONObject.toJSON(vo));

        return Res.ok().setData(vo);
    }


}

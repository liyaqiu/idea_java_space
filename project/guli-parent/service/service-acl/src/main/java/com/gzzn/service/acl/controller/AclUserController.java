package com.gzzn.service.acl.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzzn.service.acl.converter.AclRoleConverter;
import com.gzzn.service.acl.converter.AclUserConverter;
import com.gzzn.service.acl.entity.AclRoleEntity;
import com.gzzn.service.acl.entity.AclUserEntity;
import com.gzzn.service.acl.service.AclUserService;
import com.gzzn.service.acl.vo.req.AddAclRoleVo;
import com.gzzn.service.acl.vo.req.AddAclUserVo;
import com.gzzn.service.acl.vo.req.PageQueryAclRoleVo;
import com.gzzn.service.acl.vo.req.PageQueryAclUserVo;
import com.gzzn.service.acl.vo.resp.UserInfoVo;
import com.gzzn.service.utils.Res;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
        log.debug("返回用户信息 {}", JSONObject.toJSON(vo));
        return Res.ok().setData(vo);
    }

    @PostMapping
    @ApiOperation("添加用户")
    public Res addAclUser(@RequestBody @Validated AddAclUserVo vo){
        log.debug("addAclUser {}",vo);
        AclUserEntity aclUser = AclUserConverter.INSTANCE.convert(vo);
        aclUser.setGmtCreate(new Date());
        aclUser.setGmtModified(new Date());
        if (!aclUserService.save(aclUser)) {
            throw new RuntimeException("添加用户失败");
        }
        return Res.ok();
    }

    @ApiImplicitParam
    @GetMapping("/{currentPage}/{pageSize}")
    @ApiOperation("条件分页查询用户")
    public Res pageQueryAclUser(@ApiParam(name = "currentPage",value = "当前页",required = true) @PathVariable("currentPage") long currentPage,
                                @ApiParam(name = "pageSize",value = "每页大小",required = true) @PathVariable("pageSize") long pageSize,
                                PageQueryAclUserVo vo){
        log.debug("PageQueryAclRole {} {} {}",currentPage,pageSize,vo);
        Map<String, Object> map = aclUserService.pageQueryAclUser(currentPage, pageSize, vo);
        return Res.ok().setData(map);
    }

}

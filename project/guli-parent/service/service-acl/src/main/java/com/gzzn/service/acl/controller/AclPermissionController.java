package com.gzzn.service.acl.controller;

import com.gzzn.service.acl.converter.AclPermissionConverter;
import com.gzzn.service.acl.entity.AclPermissionEntity;
import com.gzzn.service.acl.service.AclPermissionService;
import com.gzzn.service.acl.vo.req.AddAclPermissionVo;
import com.gzzn.service.acl.vo.req.UpdateAclPermissionVo;
import com.gzzn.service.acl.vo.resp.QueryAuthoritiesByUsernameVo;
import com.gzzn.service.utils.Res;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

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

    @PostMapping
    @ApiOperation("添加权限")
    public Res addAclPermission(@RequestBody @Validated AddAclPermissionVo vo){
        log.debug("addAclPermission {}",vo);
        AclPermissionEntity aclPermission = AclPermissionConverter.INSTANCE.convert(vo);
        aclPermission.setGmtCreate(new Date());
        aclPermission.setGmtModified(new Date());
        aclPermissionService.saveEduTeacher(aclPermission);
        return Res.ok();
    }

    // TODO: 2022/10/9 需要做缓存的更新
    @DeleteMapping("/{id}")
    @ApiOperation("删除权限")
    public Res removeAclPermission(@PathVariable("id") String id){
        log.debug("removeAclPermission {}",id);
        aclPermissionService.removeAclPermissionById(id);
        return Res.ok();
    }

    // TODO: 2022/10/9 需要做缓存的更新
    @PutMapping
    @ApiOperation("修改讲师")
    public Res updateAclPermission(@RequestBody @Validated UpdateAclPermissionVo vo){
        log.debug("updateAclPermission {}",vo);
        AclPermissionEntity aclPermission = AclPermissionConverter.INSTANCE.convert(vo);
        aclPermission.setGmtModified(new Date());
        if(!aclPermissionService.updateById(aclPermission)){
            throw new RuntimeException("修改失败");
        }
        return Res.ok();
    }


    @GetMapping("/{username}")
    public Res queryAuthoritiesByUsername(@PathVariable("username") String username){
        log.info("queryAuthoritiesByUsername {}",username);
        QueryAuthoritiesByUsernameVo vo = aclPermissionService.queryAuthoritiesByUsername(username);
        return Res.ok().setData(vo);
    }


    @GetMapping("/all")
    public Res queryAllPermission(){
        log.info("queryAllPermission");
        List<AclPermissionEntity> permissionList = aclPermissionService.queryAllPermission();
        return Res.ok().setData(permissionList);
    }



}

package com.gzzn.service.acl.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzzn.service.acl.converter.AclRoleConverter;
import com.gzzn.service.acl.entity.AclRoleEntity;
import com.gzzn.service.acl.service.AclRoleService;
import com.gzzn.service.acl.service.AclUserService;
import com.gzzn.service.acl.vo.req.AddAclRoleVo;
import com.gzzn.service.acl.vo.req.PageQueryAclRoleVo;
import com.gzzn.service.acl.vo.req.UpdateAclRoleVo;
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
import java.util.List;
import java.util.Map;

/**
 * @author lyq
 * @date 2021/12/4 12:33
 */
@RestController
@RequestMapping("/acl/role")
@Slf4j
@Api(tags = {"角色管理"})
public class AclRoleController {

    @Autowired
    private AclRoleService aclRoleService;


    @PostMapping
    @ApiOperation("添加角色")
    public Res addAclRole(@RequestBody @Validated AddAclRoleVo vo){
        log.debug("addAclRole {}",vo);
        AclRoleEntity aclRole = AclRoleConverter.INSTANCE.convert(vo);
        aclRole.setGmtCreate(new Date());
        aclRole.setGmtModified(new Date());
        if (!aclRoleService.save(aclRole)) {
            throw new RuntimeException("添加角色失败");
        }
        return Res.ok();
    }

    // TODO: 2022/10/10 修改更新缓存
    @PutMapping
    @ApiOperation("修改角色")
    public Res updateAclRole(@RequestBody @Validated UpdateAclRoleVo vo){
        log.debug("updateAclRole {}",vo);
        AclRoleEntity aclRole = AclRoleConverter.INSTANCE.convert(vo);
        aclRole.setGmtModified(new Date());
        if(!aclRoleService.updateById(aclRole)){
            throw new RuntimeException("修改失败");
        }
        return Res.ok();
    }

    // TODO: 2022/10/9 需要做缓存的更新
    @DeleteMapping("/{id}")
    @ApiOperation("删除角色")
    public Res removeAclRole(@PathVariable("id") String id){
        log.debug("removeAclRole {}",id);
        aclRoleService.removeAclRole(id);
        return Res.ok();
    }


    @ApiImplicitParam
    @GetMapping("/{currentPage}/{pageSize}")
    @ApiOperation("条件分页查询角色")
    public Res PageQueryAclRole(@ApiParam(name = "currentPage",value = "当前页",required = true) @PathVariable("currentPage") long currentPage,
                                   @ApiParam(name = "pageSize",value = "每页大小",required = true) @PathVariable("pageSize") long pageSize,
                                   PageQueryAclRoleVo vo){
        log.debug("PageQueryAclRole {} {} {}",currentPage,pageSize,vo);

        QueryWrapper wrapper = new QueryWrapper();
        if(vo.getBeginTime()!=null){
            wrapper.ge("gmt_create", vo.getBeginTime());
        }
        if(vo.getEndTime()!=null){
            wrapper.le("gmt_create", vo.getEndTime());
        }
        if(!StringUtils.isEmpty(vo.getName())){
            wrapper.like("name", vo.getName());
        }

        wrapper.orderByDesc("gmt_create");

        IPage<AclRoleEntity> page = new Page<>(currentPage,pageSize);

        aclRoleService.page(page, wrapper);
        Map<String,Object> map = new HashMap<>();
        map.put("total", page.getTotal());
        map.put("records", page.getRecords());
        return Res.ok().setData(map);
    }


    @GetMapping("/{id}/permit/ids")
    @ApiOperation("根据角色id查询权限ids")
    public Res getPermissionIdsByRoleId(@PathVariable("id") String id){
        log.debug("getPermissionIdsByRoleId {}",id);
        List<String> permitIds = aclRoleService.queryPermissionIdsByRoleId(id);
        return Res.ok().setData(permitIds);
    }

    // TODO: 2022/10/10 修改更新缓存
    @PutMapping("/{id}/permit/ids")
    @ApiOperation("根据角色id修改权限ids")
    public Res updatePermissionIdsByRoleId(@PathVariable("id") String id,@RequestBody List<String> permitIds){
        log.debug("updatePermissionIdsByRoleId {}  {}",id,permitIds);
        aclRoleService.updatePermissionIdsByRoleId(id,permitIds);
        return Res.ok();
    }

}

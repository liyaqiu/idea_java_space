package com.gzzn.service.ucenter.controller;

import com.gzzn.service.common.utils.Res;
import com.gzzn.service.ucenter.converter.UcenterMemberConverter;
import com.gzzn.service.ucenter.entity.UcenterMemberEntity;
import com.gzzn.service.ucenter.vo.req.LoginVo;
import com.gzzn.service.ucenter.vo.req.RegisterVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.gzzn.service.ucenter.service.UcenterMemberService;

import java.util.Date;
import java.util.Map;

/**
 * @author lyq
 * @date 2021/12/4 12:33
 */
@RestController
@RequestMapping("/ucenter/member")
@Slf4j
@Api(tags = {"单点登录管理"})
public class UcenterMemberController {

    @Autowired
    private UcenterMemberService ucenterMemberService;

    @PostMapping("/login")
    @ApiOperation("登录")
    public Res login(@RequestBody @Validated LoginVo vo){
        log.debug("ssoLogin {}",vo);
        UcenterMemberEntity ucenterMember = UcenterMemberConverter.INSTANCE.convert(vo);
        Map<String, Object> map = ucenterMemberService.ssoLogin(ucenterMember);
        return Res.ok().setData(map);
    }

    @GetMapping("/info")
    @ApiOperation("获取用户信息")
    public Res getMemberInfo(@RequestHeader("token") String token){
        log.debug("getMemberInfo {}",token);
        UcenterMemberEntity ucenterMember = ucenterMemberService.getMemberInfo(token);
        return Res.ok().setData(ucenterMember);
    }

    @PostMapping("/register")
    @ApiOperation("注册用户")
    public Res register(@RequestBody @Validated RegisterVo vo){
        log.debug("register {}",vo);
        ucenterMemberService.register(vo);
        return Res.ok();
    }
}

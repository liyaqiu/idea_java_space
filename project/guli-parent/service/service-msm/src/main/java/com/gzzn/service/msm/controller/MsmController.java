package com.gzzn.service.msm.controller;

import com.gzzn.service.msm.service.MsmService;
import com.gzzn.service.utils.Res;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Pattern;
import java.util.Map;

/**
 * @author lyq
 * @date 2021/12/4 12:33
 */
@RestController
@RequestMapping("/msm")
@Slf4j
@Api(tags = {"短信管理"})
@Validated//加上此注解可以对方法参数做校验
public class MsmController {

    @Autowired
    private MsmService msmService;
    @Autowired
    StringRedisTemplate redisTemplate;

    @GetMapping("/code")
    @ApiOperation("获取验证码")
    public Res getCode(@RequestHeader("mobile") @Pattern(regexp = "^[1][3,4,5,7,8][0-9]{9}$", message = "长度为11手机号") String mobile){
        log.debug("getCode {}",mobile);
        Map<String, Object> map = msmService.sendCode(mobile);
        return Res.ok().setData(map);
    }

    @GetMapping("/verify/code")
    @ApiOperation("校验验证码")
    public Res verifyCode(@RequestHeader("mobile") @Pattern(regexp = "^[1][3,4,5,7,8][0-9]{9}$", message = "长度为11手机号") String mobile,
                          @RequestHeader("code") @Pattern(regexp = "^[0-9]{4}$", message = "长度为4验证码") String code){
        log.debug("getCode {} {}",mobile,code);
        msmService.verifyCode(mobile,code);
        return Res.ok();
    }
}

package com.a.b.czms.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author eric
 * @date 2022/3/23 16:30
 **/
@RestController
@Slf4j
@Api
public class TestController {

    @ApiOperation(value = "/test" ,notes = "测试")
    @GetMapping("/test")
    public void test(){
        log.info("{}","test");
    }
}

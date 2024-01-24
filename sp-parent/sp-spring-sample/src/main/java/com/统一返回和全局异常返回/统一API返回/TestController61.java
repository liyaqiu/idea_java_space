package com.统一返回和全局异常返回.统一API返回;

import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/demo66")
@Slf4j
public class TestController61 {



    @GetMapping("/test1")
    public CommonResult test1() throws Exception {
        if(true){
            throw  ServiceExceptionUtil.exception(ErrorCodeConstants.DEMO_ERROR,"admin","123456");
        }
        return CommonResult.success(null);
    }


    public static void main(String[] args) {
        throw  ServiceExceptionUtil.exception(ErrorCodeConstants.DEMO_ERROR,"admin","123456");
        //throw new RuntimeException(String.format("登陆失败，账户为%s，密码为%s","admin","123456"));
    }

}

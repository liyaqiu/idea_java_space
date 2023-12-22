package com.nacos.controller._1基本使用;


import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service._1基本使用.HelloWorldService;

@RestController
@RequestMapping("/HelloWorld")
public class HelloWorldController {

    @DubboReference
    private HelloWorldService helloWorldService;

    @GetMapping("/test01")
    public String test01() {
        return helloWorldService.helloWorld("eric");
    }
}

package com.nacos.controller;


import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.DemoService;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @DubboReference(group = "provider-group")
    private DemoService demoService;

    @GetMapping("/test01")
    public String test01() {
        return demoService.sayHello("eric");
    }
}

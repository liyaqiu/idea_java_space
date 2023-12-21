package com.zookeeper.controller;


import com.share.service.DemoService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {

    @DubboReference
    private DemoService demoService;

    @GetMapping("/test01")
    public String test01() {
        return demoService.sayHello("eric");
    }
}

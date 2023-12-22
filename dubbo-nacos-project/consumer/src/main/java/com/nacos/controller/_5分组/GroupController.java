package com.nacos.controller._5分组;


import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service._1基本使用.HelloWorldService;
import service._5分组.GroupService;

@RestController
@RequestMapping("/Group")
public class GroupController {

    @DubboReference(group = "test-group")
    private GroupService groupService;

    @GetMapping("/test01")
    public String test01() {
        return groupService.helloWorld("eric");
    }
}

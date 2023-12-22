package com.nacos.impl._5分组;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;
import service._5分组.GroupService;

@DubboService(group = "test-group")
public class GroupServiceImpl implements GroupService {
    @Override
    public String helloWorld(String name){
        return "hello "+name;
    }
}

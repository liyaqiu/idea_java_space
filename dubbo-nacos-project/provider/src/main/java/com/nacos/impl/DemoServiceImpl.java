package com.nacos.impl;

import org.apache.dubbo.config.annotation.DubboService;
import service.DemoService;

@DubboService(group = "provider-group")
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name){
        return "hello-"+name;
    }
}

package com.zookeeper.service.impl;

import com.share.service.DemoService;
import org.apache.dubbo.config.annotation.DubboService;

@DubboService
public class DemoServiceImpl implements DemoService {

    @Override
    public String sayHello(String name){
        return "hello-"+name;
    }
}

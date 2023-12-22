package com.nacos.impl._1基本使用;

import org.apache.dubbo.config.annotation.DubboService;
import service._1基本使用.HelloWorldService;

@DubboService
public class HelloWorldServiceImpl implements HelloWorldService {

    @Override
    public String helloWorld(String name){
        return "hello "+name;
    }
}

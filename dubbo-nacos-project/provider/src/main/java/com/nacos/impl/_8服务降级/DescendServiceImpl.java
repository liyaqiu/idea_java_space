package com.nacos.impl._8服务降级;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Value;
import service._8服务降级.DescendService;

import java.util.concurrent.TimeUnit;

@DubboService
public class DescendServiceImpl implements DescendService {

    @Value("${server.port}")
    int serverPort;

    @Override
    public String helloWorld(String name){
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "端口号为"+serverPort+" hello "+name;
    }
}

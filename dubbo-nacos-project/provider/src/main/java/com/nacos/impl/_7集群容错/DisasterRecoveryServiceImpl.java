package com.nacos.impl._7集群容错;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Value;
import service._7集群容错.DisasterRecoveryService;

@DubboService
public class DisasterRecoveryServiceImpl implements DisasterRecoveryService {

    @Value("${server.port}")
    int serverPort;

    @Override
    public String helloWorld(String name){
        return "端口号为"+serverPort+" hello "+name;
    }
}

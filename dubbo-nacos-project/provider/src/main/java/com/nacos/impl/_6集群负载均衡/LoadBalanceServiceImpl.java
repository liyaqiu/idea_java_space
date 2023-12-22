package com.nacos.impl._6集群负载均衡;

import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Value;
import service._6集群负载均衡.LoadBalanceService;

@DubboService(weight = 200)
public class LoadBalanceServiceImpl implements LoadBalanceService {

    @Value("${server.port}")
    int serverPort;

    @Override
    public String helloWorld(String name){
        return "端口号为"+serverPort+" hello "+name;
    }
}

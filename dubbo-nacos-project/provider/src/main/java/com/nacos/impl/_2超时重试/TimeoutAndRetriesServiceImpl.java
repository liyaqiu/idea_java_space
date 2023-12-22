package com.nacos.impl._2超时重试;

import org.apache.dubbo.config.annotation.DubboService;
import service._2超时重试.TimeoutAndRetriesService;

import java.util.concurrent.TimeUnit;

/*
* 超时重试这个参数，在servic端对于写操作需要注意
* */
@DubboService(timeout = -1, retries = -1)
public class TimeoutAndRetriesServiceImpl implements TimeoutAndRetriesService {

//    @Value("${server.port}")
//    int serverPort;


    @Override
    public String helloWorld(String name) {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "hello " + name;
    }
}

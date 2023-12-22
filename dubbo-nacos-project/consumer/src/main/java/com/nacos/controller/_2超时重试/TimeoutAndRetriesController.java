package com.nacos.controller._2超时重试;


import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service._1基本使用.HelloWorldService;
import service._2超时重试.TimeoutAndRetriesService;

@RestController
@RequestMapping("/TimeoutAndRetries")
public class TimeoutAndRetriesController {

    @DubboReference(timeout = -1,retries = -1)
    private TimeoutAndRetriesService timeoutAndRetriesService;

    @GetMapping("/test01")
    public String test01() {
        return timeoutAndRetriesService.helloWorld("eric");
    }
}

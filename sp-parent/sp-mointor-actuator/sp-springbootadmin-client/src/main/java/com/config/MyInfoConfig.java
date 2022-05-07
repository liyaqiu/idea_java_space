package com.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author eric
 * @date 2022/3/15 15:01
 **/
@Component
@Endpoint(id = "myconfig",enableByDefault = true)  //http://192.168.0.109/actuator/myconfig
@Slf4j
public class MyInfoConfig  {


    @ReadOperation
    public String exec() {
        log.info("MyInfoConfig");
        return "MyInfoConfig";
    }
}

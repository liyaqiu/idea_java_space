package com.aop.jdk.proxyobj;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author lyq
 * @date 2022/3/2 2:36
 */
@Component
@Slf4j
public  class Target1Impl implements Target {
    public void sayHello(){
        log.info("Target1.sayHello");
    }
}

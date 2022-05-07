package com.aop.jdk.proxyobj2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author lyq
 * @date 2022/3/2 2:36
 */
@Component
@Slf4j
public class Target2Impl implements Target2{
    public void sayHello(){
        log.info("Target2.sayHello");
    }
}

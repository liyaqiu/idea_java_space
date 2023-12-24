package com.aop.cglib.proxyobj;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author lyq
 * @date 2022/3/2 2:36
 */
@Component
@Slf4j
public class TargetImpl3 {
    public void sayHello(){
        log.info("TargetImpl3.sayHello");
    }

    public void sayHello(String name,Integer age){
        log.info("TargetImpl3.sayHello {}  {}",name,age);
    }
}

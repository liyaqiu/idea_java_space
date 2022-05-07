package com.aop.cglib.proxyobj2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author lyq
 * @date 2022/3/2 2:36
 */
@Component
@Slf4j
public class TargetImpl4  {
    public void sayHello(){
        log.info("TargetImpl4.sayHello");
    }
}

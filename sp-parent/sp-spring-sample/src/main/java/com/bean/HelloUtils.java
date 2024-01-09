package com.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author lyq
 * @date 2022/2/26 9:53
 */
@Slf4j
@Component
@Scope //Singleton ,Prototyp,request,session
public class HelloUtils {

    public void sayHello(){
        log.info("我是第三方的包被ComponentScan引入 。。。say hello.");
    }
}

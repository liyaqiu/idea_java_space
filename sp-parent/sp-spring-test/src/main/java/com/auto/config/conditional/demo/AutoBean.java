package com.auto.config.conditional.demo;

import lombok.extern.slf4j.Slf4j;

/**
 * @author eric
 * @date 2022/3/15 18:10
 * 自动装配
 **/
@Slf4j
public class AutoBean {
    public AutoBean(){
        log.error("AutoBean init");
    }
}

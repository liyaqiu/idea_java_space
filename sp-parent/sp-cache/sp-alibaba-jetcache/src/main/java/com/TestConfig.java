package com;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lyq
 * @date 2022/3/12 9:43
 */
@Configuration(proxyBeanMethods = false)
@Slf4j
public class TestConfig {

    @Bean
    public String test0(){
               log.info("test0");
               return "nihao";
    }

    @Bean
    public void test1(){
        log.info("test1 {}",test0());
    }
}

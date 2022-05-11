package com.ConfigurationProxyBeanMethods;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author eric
 * @date 2022/5/11 12:15
 * proxyBeanMethods = true,成为代理对象，以后被标注@Bean的注解的方法都是从容器中获取对象
 * com.configuration.Entity1@76ed7573
 * com.configuration.Entity1@76ed7573
 * com.configuration.Entity1@76ed7573
 **/

@Configuration(proxyBeanMethods = true)
@Slf4j
public class TestProxyBeanMethods {

    @Bean
    //@Scope("prototype")
    public Entity1 test1(){
        return new Entity1();
    }

    @Bean
    public Entity2 test2(){

        log.info("Entity2---{}",test1());
        log.info("Entity2---{}",test1());
        log.info("Entity2---{}",test1());
        return new Entity2();
    }

}

package com.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author lyq
 * @date 2022/3/11 12:37
 * proxyBeanMethods = true  用代理方式生成bean  com.config.MyConfig$$EnhancerBySpringCGLIB$$1406e796
 * proxyBeanMethods = false  普通的bean  com.config.MyConfig
 *
 */
@Configuration(proxyBeanMethods = false)
public class MyConfig {

    @Bean
    public ConfigBean ConfigBean(){
           return new ConfigBean();
    }


    public static class ConfigBean{

    }
}

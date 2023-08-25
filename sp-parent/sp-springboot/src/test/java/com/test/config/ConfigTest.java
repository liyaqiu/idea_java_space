package com.test.config;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author lyq
 * @date 2022/3/8 13:55
 */
@Component
public class ConfigTest {
    @Bean
    public String liyaqiu(){
        return "hello world";
    }
}

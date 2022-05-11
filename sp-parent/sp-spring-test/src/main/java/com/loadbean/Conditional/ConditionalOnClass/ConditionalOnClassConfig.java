package com.loadbean.Conditional.ConditionalOnClass;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author eric
 * @date 2022/5/11 15:23
 **/
@Configuration
@Slf4j
public class ConditionalOnClassConfig {

    @Bean
    @ConditionalOnClass(name = "com.loadbean.Conditional.ConditionalOnClass.ConditionalEntity")
    //@ConditionalOnBean
    public ConditionalOnClassEntity ConditionalOnClassEntity(){
        return new ConditionalOnClassEntity();
    }
}

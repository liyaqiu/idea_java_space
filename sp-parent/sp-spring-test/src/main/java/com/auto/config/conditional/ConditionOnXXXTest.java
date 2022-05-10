package com.auto.config.conditional;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author eric
 * @date 2022/3/17 12:01
 **/
@Component
@Slf4j
public class ConditionOnXXXTest {

    @Autowired
    Myclass myclass;


    @Bean
    @ConditionalOnClass(name = {"com.auto.config.conditional.Myclass1"})
    //@ConditionalOnMissingBean
    //@ConditionalOnProperty
    public Myclass conditionOnClassTest1(){
        log.info("conditionOnClassTest1...");
        return new Myclass();
    }

}

@Slf4j
class Myclass{ }


class Myclass1{}



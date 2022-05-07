package com.auto.config.conditional.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * @author eric
 * @date 2022/3/15 18:11
 **/
@Slf4j
public class MyCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
//        try {
//            Class.forName("com.test.XXX");
//            log.error("MyCondition matches");
//        } catch (ClassNotFoundException e) {
//            //e.printStackTrace();
//            return false;
//        }
        log.error("MyCondition matches");
        return true;
    }
}

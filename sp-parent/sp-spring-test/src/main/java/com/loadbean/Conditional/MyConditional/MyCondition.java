package com.loadbean.Conditional.MyConditional;

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
        try {
            //判断当前类是否存在
            Class<?> forName = Class.forName("com.loadbean.Conditional.MyConditional.ConditionRedisEntity");
            if(forName!=null){
                return true;
            }
        } catch (ClassNotFoundException e) {
            //e.printStackTrace();
            return false;
        }
        return false;
    }
}

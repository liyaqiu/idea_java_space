package com.loadbean.Conditional.MyConditional;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * @author eric
 * @date 2022/3/17 11:29
 **/
@Configuration(proxyBeanMethods = false)
@Conditional({MyCondition.class}) //加载类上，所有的方法都必须满足
public class MyConditionConfig {

    @Bean
    //@Conditional({MyCondition.class})
    public ConditionRedisPoolEntity conditionRedisPoolEntity(){
        return new ConditionRedisPoolEntity();
    }
}

package com.amqp.defautl;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * 简单队列创建
 * @author lyq
 * @date 2022/1/14 3:18
 */
@Configuration
public class DefaultExchangeConfig {

    @Bean
    public Queue workQueue(){
        return new Queue("sp.work.queue");
    }


}

package com.amqp.queue;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lyq
 * @date 2022/2/22 22:13
 */
@Configuration
public class NormalQueue {
    @Bean
    public Queue normalqueue(){
        return QueueBuilder.durable("normal.queue")
                .build();
    }

}

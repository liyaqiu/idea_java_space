package com.amqp.direct.dead;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lyq
 * @date 2022/2/22 20:21
 * 利用ttl超时后的消息转发到死信交换机
 * 1.消息TTL超时或者队列TTL超时
 * 2.reject或者nack的消息（消费者失败重试）
 * 3.消息队列满了
 */
@Configuration
public class TTLQueue {

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("ttl.direct.exchange");
    }

    @Bean
    public Queue queue() {
        return QueueBuilder.durable("ttl.queue") //队列名称，并且持久化
                .ttl(10000) //10秒超时
                .deadLetterExchange("sp.direct.exchange.dl") //指定死信交换机
                .deadLetterRoutingKey("dl") //死信交换机的路由key
                .build();
    }

    @Bean
    public Binding test3(){
        return BindingBuilder.bind(queue()).to(directExchange()).with("ttl");
    }


}



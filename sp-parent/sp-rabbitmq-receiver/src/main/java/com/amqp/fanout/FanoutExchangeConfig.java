package com.amqp.fanout;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 广播（fanout）配置
 * 将queue1和queue2绑定到fanout交换机
 * @author lyq
 * @date 2022/1/14 3:18
 */
@Configuration
public class FanoutExchangeConfig {

    @Bean
    public Queue queue1(){
        return new Queue("sp.fanout.queue1");
    }
    @Bean
    public Queue queue2(){
        return new Queue("sp.fanout.queue2");
    }
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("sp.fanout.exchange");
    }
    @Bean
    public Binding bindQueue1(Queue queue1, FanoutExchange fanoutExchange){
        return  BindingBuilder.bind(queue1).to(fanoutExchange);
    }
    @Bean
    public Binding bindQueue2(Queue queue2, FanoutExchange fanoutExchange){
        return  BindingBuilder.bind(queue2).to(fanoutExchange);
    }

}

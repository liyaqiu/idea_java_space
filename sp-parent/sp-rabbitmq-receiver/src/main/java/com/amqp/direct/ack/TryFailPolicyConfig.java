package com.amqp.direct.ack;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.retry.MessageRecoverer;
import org.springframework.amqp.rabbit.retry.RepublishMessageRecoverer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @author lyq
 * @date 2022/2/22 18:59
 * 消费者失败消息策略
 */
@Configuration
public class TryFailPolicyConfig {

    @Bean
    public DirectExchange errorExchange() {
        return new DirectExchange("direct.exchange.error");
    }

    @Bean
    public Queue errorQueue() {
        return new Queue("queue.error");
    }

    @Bean
    public Binding ExchangeBindQueue() {
        return BindingBuilder.bind(errorQueue()).to(errorExchange()).with("errormsg");
    }


    /**
     *
     */
    @Bean
    public MessageRecoverer tryPolicy(RabbitTemplate rabbitTemplate) {
        //重试消耗尽够直接reject，并且丢弃消息，默认采用此方式
        //return new RejectAndDontRequeueRecoverer();

        //重试消耗尽后，返回nack，消息重新进入队列
        //new ImmediateRequeueMessageRecoverer();


        //重试消耗尽够，将失败消息投递到别的交换机队列
        return new RepublishMessageRecoverer(rabbitTemplate, "direct.exchange.error", "errormsg");

    }


}

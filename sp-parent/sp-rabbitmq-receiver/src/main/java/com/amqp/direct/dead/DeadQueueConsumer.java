package com.amqp.direct.dead;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author lyq
 * @date 2022/2/22 20:14
 * 普通队列声明
 */
@Component
@Slf4j
public class DeadQueueConsumer {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "sp.direct.dl.queue"),
            exchange = @Exchange(name = "sp.direct.exchange.dl",type = ExchangeTypes.DIRECT),
            key = {"dl"}
    ))
    public void listenDLQueue(String message){
        log.info("用死信交换机做延迟接收消息：{}",message);
    }
}

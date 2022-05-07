package com.amqp.direct.ack;

import com.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * DirectQueue   根据key来做路由
 * @author lyq
 * @date 2022/1/14 1:04
 */
//@Component
@Slf4j
public class DirectQueueACKConsumer {


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "sp.direct.queue2"),
            exchange = @Exchange(name = "sp.direct.exchange",type = ExchangeTypes.DIRECT),
            key = {"white","whiteAndBalck"}
    ))
    public void ListenQueueWork2(UserEntity message) throws InterruptedException {
        log.info("sp.direct.queue2-->{}",message);
        System.out.println(1/0);
    }

}

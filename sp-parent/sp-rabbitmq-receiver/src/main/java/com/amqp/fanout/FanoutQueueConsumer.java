package com.amqp.fanout;

import com.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * FanoutQueue   1条消息发送到交换机，然后路由到每一个队列，在利用多个消费者消费不同队列的消息
 * @author lyq
 * @date 2022/1/14 1:04
 */
//@Component
@Slf4j
public class FanoutQueueConsumer {

    @RabbitListener(queues = "sp.fanout.queue1")
    public void ListenQueueWork1_1(UserEntity message) throws InterruptedException {
        log.info("  sp.fanout.queue1-1-->{}",message);
        Thread.sleep(10);
    }
    @RabbitListener(queues = "sp.fanout.queue1")
    public void ListenQueueWork1_2(UserEntity message) throws InterruptedException {
        log.info("  sp.fanout.queue1-2-->{}",message);
        Thread.sleep(10);
    }
    @RabbitListener(queues = "sp.fanout.queue2")
    public void ListenQueueWork2(UserEntity message) throws InterruptedException {
        log.error("sp.fanout.queue2-->{}",message);
        Thread.sleep(20);
    }

}

package com.amqp.defautl;

import com.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * workQueue   100条消息分散到多个消费者消费
 * @author lyq
 * @date 2022/1/14 1:04
 */
//@Component
@Slf4j
public class WorkQueueConsumer {

    @RabbitListener(queues = "sp.work.queue")
    public void ListenQueueWork1(UserEntity message) throws InterruptedException {
        log.info("work1-->{}",message);
        Thread.sleep(10);
    }
    @RabbitListener(queues = "sp.work.queue")
    public void ListenQueueWork2(UserEntity message) throws InterruptedException {
        log.error("work2-->{}",message);
        Thread.sleep(20);
    }



}

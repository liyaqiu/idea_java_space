package com.amqp.topic;

import com.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

/**
 * TopicQueue   根据key来做路由,支持通配符
 *      通配符只能设置一个，例如【 #.china,*.china】不允许组合使用【***.china,*a*.china】
 *      [*] 代表1个单词
 *      [#] 代表0和或者多个
 * @author lyq
 * @date 2022/1/14 1:04
 */
//@Component
@Slf4j
public class TopicQueueConsumer {

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "sp.topic.queue1"),
            exchange = @Exchange(name = "sp.topic.exchange",type = ExchangeTypes.TOPIC),
            key = {"china.#"}
    ))
    //@SendTo 将方法的返回值进行从新发送到别的队列
    public void ListenQueueWork1_1(UserEntity message) throws InterruptedException {
        log.info("  sp.topic.queue1-1-->{}",message);
        Thread.sleep(10);
    }
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "sp.topic.queue1"),
            exchange = @Exchange(name = "sp.topic.exchange",type = ExchangeTypes.TOPIC),
            key = {"china.#"}
    ))
    public void ListenQueueWork1_2(UserEntity message) throws InterruptedException {
        log.info("  sp.topic.queue1-2-->{}",message);
        Thread.sleep(10);
    }


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "sp.topic.queue2"),
            exchange = @Exchange(name = "sp.topic.exchange",type = ExchangeTypes.TOPIC),
            key = {"#.news"}
    ))
    public void ListenQueueWork2(UserEntity message) throws InterruptedException {
        log.error("sp.topic.queue2-->{}",message);
        Thread.sleep(20);
    }


    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "sp.topic.queue3"),
            exchange = @Exchange(name = "sp.topic.exchange",type = ExchangeTypes.TOPIC),
            key = {"*.news"}
    ))
    public void ListenQueueWork3(UserEntity message) throws InterruptedException {
        log.error("      sp.topic.queue3-->{}",message);
        Thread.sleep(20);
    }

}

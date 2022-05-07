package com.amqp.queue;

import com.entity.UserEntity;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.annotation.Argument;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author lyq
 * @date 2022/2/22 22:13
 */
@Configuration
public class ClusterQueue {
    @Bean
    public Queue clusterqueue(){
        return QueueBuilder.durable("cluster.queue")
                .quorum()//集群仲裁队列
                .build();
    }
    @RabbitListener(bindings = @QueueBinding(
            value = @org.springframework.amqp.rabbit.annotation.Queue(name = "cluster.direct.queue2",arguments = {@Argument(name = "x-queue-type",value = "quorum")}),
            exchange = @Exchange(name = "cluster.exchange2",type = ExchangeTypes.DIRECT),
            key = {"black","whiteAndBalck"}
    ))
    public void ListenQueueWork1_2(UserEntity message) throws InterruptedException {

    }
}

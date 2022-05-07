package test.ack;

import com.SenderApplication;
import com.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

/**
 * @author lyq
 * @date 2022/1/14 0:13
 */
@SpringBootTest(classes = SenderApplication.class)
@Slf4j
public class AMQPACKSender {

    /*@Autowired
    AmqpTemplate amqpTemplate;*/
    @Autowired
    RabbitTemplate rabbitTemplate;

    /**
     * ConfirmCallback 消息是否到达交换机
     * */
    @Test
    public void sendToDirectExchange() throws InterruptedException {
        UserEntity userEntity = new UserEntity();
        userEntity.setName("lyq");
        userEntity.setAge("31");

        //把回执id封装到 CorrelationData
        CorrelationData correlationData = new CorrelationData(UUID.randomUUID().toString());
        //ConfirmCallback 消息是否到达交换机
        correlationData.getFuture().addCallback(confirm -> {
            if (confirm.isAck()) {
                //ack
                log.info("消息发送到交换机成功 回执id:{}",correlationData.getId());
            }else{
                //nack
                log.error("消息发送到交换机失败 回执id:{}",correlationData.getId());
                //可以实现消息重发
            }
        }, throwable -> {
            log.error("未知异常，发送失败。 回执id:{},异常原因",correlationData.getId(),throwable);
        });

        /**
         *    sp.direct.queue1 key  black  whiteAndBalck
         *    sp.direct.queue2 key  white  whiteAndBalck
         * */

        rabbitTemplate.convertAndSend("sp.direct.exchange", "white", userEntity,correlationData);
        log.info("发送完成....");
    }



}

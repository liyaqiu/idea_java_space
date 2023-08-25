package com.test;

import com.SenderApplication;
import com.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageDeliveryMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.nio.charset.StandardCharsets;

/**
 * @author lyq
 * @date 2022/1/14 0:13
 */
@SpringBootTest(classes = SenderApplication.class)
@Slf4j
public class AMQPSender {

    @Autowired
    AmqpTemplate amqpTemplate;
    /*@Autowired
    RabbitTemplate rabbitTemplate*/


    @Test
    public void sendToBaseQueue(){
        UserEntity userEntity = new UserEntity();
        userEntity.setName("lyq");
        userEntity.setAge("12");
        amqpTemplate.convertAndSend("sp.work.queue", userEntity);
        log.info("发送完成....");
    }



    @Test
    public void sendToDefaultExchange(){
        UserEntity userEntity = new UserEntity();
        userEntity.setName("lyq");
        for (int i = 0; i <50 ; i++) {
            userEntity.setAge(i+"");
            amqpTemplate.convertAndSend("sp.work.queue", userEntity);
        }
        log.info("发送完成....");
    }

    @Test
    public void sendToFanoutExchange(){
        UserEntity userEntity = new UserEntity();
        userEntity.setName("lyq");
        for (int i = 0; i <5 ; i++) {
            userEntity.setAge(i+"");
            amqpTemplate.convertAndSend("sp.fanout.exchange", null, userEntity);

        }
        log.info("发送完成....");
    }

    @Test
    public void sendToDirectExchange(){
        UserEntity userEntity = new UserEntity();
        userEntity.setName("lyq");

        /**
         *    sp.direct.queue1 key  black  whiteAndBalck
         *    sp.direct.queue2 key  white  whiteAndBalck
         *
         * */
        for (int i = 0; i <5 ; i++) {
            userEntity.setAge(i+"");
            amqpTemplate.convertAndSend("sp.direct.exchange", "white", "every one__"+i);
            //amqpTemplate.convertAndSend("sp.direct.exchange", "whiteAndBalck", userEntity);
        }
        log.info("发送完成....");
    }

    @Test
    public void sendToTopicExchange(){
        UserEntity userEntity = new UserEntity();
        userEntity.setName("lyq");
        userEntity.setAge("12");
        /**
         *    sp.topic.queue1 key  china.#
         *    sp.topic.queue2 key  #.news
         *    sp.topic.queue3 key  ***.news
         *      [*] 代表1个单词
         *      [#] 代表0和或者多个
         * */
        for (int i = 0; i <3 ; i++) {
            userEntity.setAge(i+"");
            //amqpTemplate.convertAndSend("sp.topic.exchange", "china.news", "every one__"+i);
            amqpTemplate.convertAndSend("sp.topic.exchange", "c.news", userEntity);
        }
        log.info("发送完成....");
    }


    @Test
    public void test11111(){
        Message message1 = MessageBuilder.withBody("hello-worldhello-worldhello-worldhello-worldhello-worldhello-worldhello-worldhello-worldhello-world".getBytes(StandardCharsets.UTF_8))
                .setDeliveryMode(MessageDeliveryMode.PERSISTENT).build();
        Message message2 = MessageBuilder.withBody("hello-worldhello-worldhello-worldhello-worldhello-worldhello-worldhello-worldhello-worldhello-world".getBytes(StandardCharsets.UTF_8))
                .setDeliveryMode(MessageDeliveryMode.NON_PERSISTENT).build();

        for (int i = 0; i <100 ; i++) {
            amqpTemplate.convertAndSend("lazy.queue", message1);
        }
        for (int i = 0; i <200000 ; i++) {
            amqpTemplate.convertAndSend("lazy.queue", message2);
        }
        log.info("发送完成....");
    }

}

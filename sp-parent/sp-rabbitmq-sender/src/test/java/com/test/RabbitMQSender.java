package com.test;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author lyq
 *
 * @date 2022/1/13 7:07
 */
@Slf4j
public class RabbitMQSender {
    @Test
    public void test0(){
        log.info("发送！");
        String QUEUE_NAME = "hello-queue";
        ConnectionFactory factory = new ConnectionFactory();
        factory.setPort(5672);
        factory.setHost("192.168.0.203");
        factory.setVirtualHost("/");//逻辑空间
        factory.setUsername("liyaqiu");
        factory.setPassword("123456");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            String message = "Hello World!";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            log.info("发送成功！");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
    }
}

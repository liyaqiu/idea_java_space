package com.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * @author lyq
 * @date 2022/2/22 4:02
 * ReturnCallback 消息到了交换机，消息是否能路由到队列
 */
@Configuration
@Slf4j
public class RabbitMQReturnCallback implements ApplicationContextAware {

    /**
     * 每个RabbitTemplate只能配置一个ReturnCallback,而spring管理的RabbitTemplate默认是单例
     * 因此整个RabbitTemplate的ReturnCallback只能出现一次。
     * */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        RabbitTemplate rabbitTemplate = applicationContext.getBean(RabbitTemplate.class);
        rabbitTemplate.setReturnCallback((message, replyCode, replyText, exchange, routingKey) -> {
            log.error("消息已经到了交换机，但是路由到队列失败: 应答码:{} 原因:{} 交换机:{} 路由器:{} 消息:{} "
                    , replyCode, replyText, exchange, routingKey,message);
            //可以实现消息重发
        });

    }
}

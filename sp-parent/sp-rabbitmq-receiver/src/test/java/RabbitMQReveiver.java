import com.rabbitmq.client.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @author lyq
 *
 * @date 2022/1/13 7:07
 */
public class RabbitMQReveiver {
    @Test
    public void test0() throws IOException, TimeoutException {
        String QUEUE_NAME = "hello-queue";
        ConnectionFactory factory = new ConnectionFactory();
        factory.setPort(5672);
        factory.setHost("192.168.0.203");
        factory.setVirtualHost("/");//逻辑空间
        factory.setUsername("liyaqiu");
        factory.setPassword("123456");

        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        channel.basicConsume(QUEUE_NAME, true, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("收到的消息 '" + message + "'");
            }
        });


    }
}

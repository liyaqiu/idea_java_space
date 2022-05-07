import com.ReceiverApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author lyq
 * @date 2022/1/14 0:13
 */
@SpringBootTest(classes = ReceiverApplication.class)
@Slf4j
public class AMQPReceiver {


    @Autowired
    AmqpTemplate amqpTemplate;

    @Test
    public void test0(){
        String msg = (String) amqpTemplate.receiveAndConvert("hello-queue");
        log.info("收到的消息:{}",msg);
    }



}

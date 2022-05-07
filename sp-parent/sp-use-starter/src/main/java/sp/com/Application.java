package sp.com;

import lombok.extern.slf4j.Slf4j;
import my.com.config.MyRedisConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import redis.clients.jedis.Jedis;

/**
 * @author eric
 * @date 2022/3/18 13:40
 **/
@SpringBootApplication
@Slf4j
public class Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
//        Jedis jedis = context.getBean(Jedis.class);
//        log.info("jedis {}",jedis);
    }

    /*@Bean
    public Jedis myjedis(MyRedisConfig myRedisConfig){
        log.info("use starter 提供了 jedis。。。。。。");
        log.info("{}",myRedisConfig);
        return new Jedis(myRedisConfig.getHost(),myRedisConfig.getPort());
    }*/

}

package my.com;

import lombok.extern.slf4j.Slf4j;
import my.com.config.MyRedisConfig;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.Jedis;

import javax.management.MXBean;

/**
 * @author eric
 * @date 2022/3/18 13:29
 **/
//@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(MyRedisConfig.class)
@ConditionalOnClass(Jedis.class)
@Slf4j
public class MyRedisAutoConfiguretion {

    @Bean
    @ConditionalOnMissingBean(type = "redis.clients.jedis.Jedis")
    public Jedis myjedis(MyRedisConfig myRedisConfig){
        log.info("MyRedisAutoConfiguretion 提供了, jedis。。。。。。");
        log.info("{}",myRedisConfig);
        return new Jedis(myRedisConfig.getHost(),myRedisConfig.getPort());
    }
}

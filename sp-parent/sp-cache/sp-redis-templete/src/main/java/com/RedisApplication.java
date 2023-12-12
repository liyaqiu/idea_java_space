package com;

import io.lettuce.core.ReadFrom;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.LettuceClientConfigurationBuilderCustomizer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author lyq
 * @date 2021/12/4 12:34
 */
@SpringBootApplication
public class RedisApplication {



    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(RedisApplication.class, args);
        //连接池测试
        /*
         StringRedisTemplate redisTemplate = context.getBean(StringRedisTemplate.class);
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    long startTime = System.currentTimeMillis();
                    for (int j = 0; j <200 ; j++) {
                        redisTemplate.opsForValue().set(Thread.currentThread().getName()+j, Thread.currentThread().getName()+j);
                    }
                    System.out.println(Thread.currentThread().getName()+"总耗时"+(System.currentTimeMillis()-startTime));
                }
            });

        }*/

    }



    /*
    redis-sentinel哨兵集群 和 分片集群
    ReadFrom.MASTER   从主节点读取
    ReadFrom.MASTER_PREFERRED 优先master读取，master不可以在读取slave
    ReadFrom.REPLICA 从slave读取
    ReadFrom.REPLICA_PREFERRED 优先slave读取，slave不可以在读取master
    */
    @Bean
    public LettuceClientConfigurationBuilderCustomizer test0(){
        return configBuilder -> configBuilder.readFrom(ReadFrom.REPLICA_PREFERRED);
    }
}

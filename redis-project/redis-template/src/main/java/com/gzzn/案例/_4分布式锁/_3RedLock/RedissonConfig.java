package com.gzzn.案例._4分布式锁._3RedLock;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/**
 * 在Redis的分布式环境中，我们假设有N个Redis Master。这些节点完全互相独立，不存在主从复制或者其他集群协调机制
 *
 * 不管是单机、主从、哨兵集群、分片集群都看做是一个master。
 * 可以相互组合，但最好是同类，比如3个单机版的(互不相干的)，或者是3个分片集群(互不相干的)，或者是3个哨兵集群(互不相干的)
 *     - https://zhuanlan.zhihu.com/p/374732293 比较好的博客
 *     - https://blog.csdn.net/weixin_47533244/article/details/131391276 参考案例
 * */
@Configuration
public class RedissonConfig {

    @Bean
    public Redisson redisson1(){
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://192.168.88.11:5551")
                //.setTimeout(3000)
                //.setConnectionPoolSize(10)
                //.setConnectionMinimumIdleSize(3)
                .setDatabase(0)
                .setPassword("123456");
        return (Redisson) Redisson.create(config);
    }
    @Bean
    public Redisson redisson2(){
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://192.168.88.11:5552")
                //.setTimeout(3000)
                //.setConnectionPoolSize(10)
                //.setConnectionMinimumIdleSize(3)
                .setDatabase(0)
                .setPassword("123456");
        return (Redisson) Redisson.create(config);
    }
    @Bean
    public Redisson redisson3(){
        Config config = new Config();
        config.useSingleServer()
                .setAddress("redis://192.168.88.11:5553")
                .setTimeout(3000)
                //.setConnectionPoolSize(10)
                //.setConnectionMinimumIdleSize(3)
                .setDatabase(0)
                .setPassword("123456");
        return (Redisson) Redisson.create(config);
    }



    /*单机配置*/
    /*@Bean
    public Redisson redisson1(){
        Config config = new Config();
        config.useSingleServer().setAddress("redis://192.168.88.11:6661")
                .setDatabase(0)
                .setPassword("123456");
        return (Redisson) Redisson.create(config);
    }*/


    /**
     哨兵集群配置
     * */
    /*@Bean
    public Redisson redisson2(){
        Config config = new Config();
        config.useSentinelServers()
                .addSentinelAddress("redis://192.168.88.11:9991","redis://192.168.88.11:9992","redis://192.168.88.11:9993")
                .setMasterName("mymaster")
                .setDatabase(2)
                .setPassword("123456");
        return (Redisson) Redisson.create(config);
    }*/

    /*
     分片集群配置
     * */
    /*@Bean
    public Redisson redisson3(){
        Config config = new Config();
        config.useClusterServers().addNodeAddress(
                "redis://192.168.88.11:8881"
                        ,"redis://192.168.88.11:8882"
                        ,"redis://192.168.88.11:8883"
                        ,"redis://192.168.88.11:8884"
                        ,"redis://192.168.88.11:8885"
                        ,"redis://192.168.88.11:8886")
                .setPassword("123456");
        return (Redisson) Redisson.create(config);
    }*/
}

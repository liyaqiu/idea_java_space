package com;


import com.entity.Goods;
import com.entity.GoodsStock;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author lyq
 * @date 2021/12/4 12:34
 */
@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public Cache<String, Goods> test0(){
        Cache<String, Goods> cache = Caffeine.newBuilder()
                .initialCapacity(100)
                .maximumSize(1000)//超过1000以后会保留热数据，其他都会清除
                .build();
        return cache;
    }

    @Bean
    public Cache<String, GoodsStock> test1(){
        Cache<String, GoodsStock> cache = Caffeine.newBuilder()
                .initialCapacity(100)
                .maximumSize(1000)//超过1000以后会保留热数据，其他都会清除
                .build();
        return cache;
    }

}

package com.gzzn.案例._1UV基数统计;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
        @RequestMapping("/HyperLogLog")
        public class HyperLogLogController {

            @Autowired
            private RedisTemplate<String,Object> redisTemplate;

            /**
             * 可以统计每天用户IP地址访问量，HyperLogLog密集型算法，做基数统计
             */
            @GetMapping("test01")
            public void test(){
                redisTemplate.opsForHyperLogLog().add("day_01",new Random().nextInt(10000));
                System.out.println(redisTemplate.opsForHyperLogLog().size("day_01"));
    }
}

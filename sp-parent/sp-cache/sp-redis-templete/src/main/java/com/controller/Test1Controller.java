package com.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lyq
 * @date 2022/1/6 11:36
 */
@RestController
@Slf4j
public class Test1Controller {



    @Autowired
    StringRedisTemplate redisTemplate;

    @GetMapping("/getkey/{key}")
    public String test1(@PathVariable String key){
        log.debug("key:{}",key);
        return redisTemplate.opsForValue().get(key);
    }
    @GetMapping("/setkey/{key}/{value}")
    public String test2(@PathVariable String key,@PathVariable String value){


        redisTemplate.opsForValue().set(key, value);
        log.debug("redisTemplate{} key:{},valye:{}",redisTemplate,key,value);
        return "name:"+key+",age:"+value;
    }


}

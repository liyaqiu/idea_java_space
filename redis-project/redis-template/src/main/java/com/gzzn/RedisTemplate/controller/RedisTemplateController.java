package com.gzzn.RedisTemplate.controller;


import cn.hutool.json.JSONUtil;
import com.gzzn.RedisTemplate.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashMap;

@RestController
@RequestMapping("/RedisTemplate")
@Slf4j
public class RedisTemplateController {

    @Autowired
    RedisTemplate redisTemplate;

    @GetMapping("/test11")
    public void String类11型() throws Exception {
        redisTemplate.opsForValue().set("k6","v6");
        System.out.println(redisTemplate.opsForValue().get("k6"));
    }

    @GetMapping("/test1")
    public void String类型() throws Exception {
        ValueOperations<String, Person> opsForValue = redisTemplate.opsForValue();
        opsForValue.set("k1", new Person("eric",19));
        Person person = opsForValue.get("k1");
        System.out.println(person);
    }
    @GetMapping("/test2")
    public void List类型() {
        ListOperations<String, Object> opsForList = redisTemplate.opsForList();
        opsForList.rightPush("k2","1");
        opsForList.rightPush("k2", "2");
        opsForList.rightPush("k2", "3");
        System.out.println(opsForList.range("k2", 0, -1));
        System.out.println(opsForList.rightPop("k2"));
    }

    @GetMapping("/test3")
    public void Hash类型() {
        HashOperations<String, String, Object> opsedForHash = redisTemplate.opsForHash();
        opsedForHash.putAll("k3", new HashMap<String, Object>() {
            {
                put("person",new Person("eric",18));
                put("name", "eric");
                put("age", "18");
                put("sex", "男");
            }
        });
        System.out.println(opsedForHash.entries("k3"));
    }

    @GetMapping("/test4")
    public void Set类型() {
        SetOperations<String, String> opsForSet = redisTemplate.opsForSet();
        opsForSet.add("k4", "1", "2", "1", "3");
        System.out.println(opsForSet.members("k4"));
    }

    @GetMapping("/test5")
    public void Sorted_Set类型() {
        ZSetOperations<String, Object>  opsForZSet = redisTemplate.opsForZSet();
        opsForZSet.add("k5", "33",10.4D);
        opsForZSet.add("k5", "11",10.2D);
        opsForZSet.add("k5", "22",10.3D);
        System.out.println(opsForZSet.range("k5", 0, -1));
    }

}

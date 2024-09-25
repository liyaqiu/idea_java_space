package com.gzzn;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundValueOperations;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author lyq
 * @date 2021/12/4 15:31
 * <p>
 * 项目管理系统数据导入
 */
@SpringBootTest(classes = Launcher.class)
@Slf4j
public class TestRedis {

    @Autowired
    RedisTemplate redisTemplate;


    @Test
    public void test1() {
        redisTemplate.opsForValue().set("k1","v1");
    }

    @Test
    public void test2() {
        redisTemplate.boundValueOps("k4").set("11111111111111");
        System.out.println(redisTemplate.boundValueOps("k4").get());
    }


}

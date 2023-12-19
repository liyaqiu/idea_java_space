package com.gzzn.案例._4分布式锁._3RedLock;

import org.redisson.Redisson;
import org.redisson.RedissonLock;
import org.redisson.RedissonMultiLock;
import org.redisson.RedissonRedLock;
import org.redisson.api.RLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * 可重入锁
 */
@RestController
@RequestMapping("/RedLock")
public class RedissonLockController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private Redisson redisson1;
    @Autowired
    private Redisson redisson2;
    @Autowired
    private Redisson redisson3;

    private final String SHOP_KEY = "SHOP_KEY";

    private final String LOCK_KEY = "RED_LOCK_KEY";

    @GetMapping("test01")
    public String test01() {
        //RLock redLock = new RedissonMultiLock(
        //RLock redLock = redisson1.getMultiLock(
        //前面2种实现，如果宕机一台master，会导致整个集群加锁失败，暂且可能是版本bug
        RLock redLock = new RedissonRedLock(
                    redisson1.getLock(LOCK_KEY),
                    redisson2.getLock(LOCK_KEY),
                    redisson3.getLock(LOCK_KEY)
                );
        redLock.lock();
        //redLock.lock(10, TimeUnit.SECONDS);
        //boolean result = redLock.tryLock(1, 20, TimeUnit.SECONDS);
        System.out.println("加锁成功");
        try {
            //测试看门狗(watch dog)自动续期
            //TimeUnit.SECONDS.sleep(30);
            return buyShop();
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            redLock.unlock();
            System.out.println("解锁成功");
        }
    }

    private String buyShop() {
        String count = redisTemplate.opsForValue().get(SHOP_KEY);
        int shopNumber = count == null ? 0 : Integer.parseInt(count);
        //库存为0直接返回
        if (shopNumber <= 0) {
            return "当前库存为0";
        }
        //减少库存
        redisTemplate.opsForValue().set(SHOP_KEY, String.valueOf(--shopNumber));
        return "购买成功，当前库存剩余" + shopNumber;
    }


}


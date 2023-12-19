package com.gzzn.案例._4分布式锁._2可重入锁;

import cn.hutool.core.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 可重入锁
 */
@RestController
@RequestMapping("/HashLock")
public class Hash类型LockController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private final String SHOP_KEY = "SHOP_KEY";

    private final String KEY = "LOCK_KEY";


    @GetMapping("test01")
    public String test01() {
        RedisLock redisLock = RedisLock.builder()
                .key(KEY)
                .hKey(IdUtil.randomUUID() + ":" + Thread.currentThread().getId())
                .expireTime(30)
                .redisTemplate(redisTemplate)
                .build();
        try {
            redisLock.lock();
            //测试自动续期
            //TimeUnit.SECONDS.sleep(120);
            //加锁成功
            String count = redisTemplate.opsForValue().get(SHOP_KEY);
            int shopNumber = count == null ? 0 : Integer.parseInt(count);
            //库存为0直接返回
            if (shopNumber <= 0) {
                return "当前库存为0";
            }
            //减少库存
            redisTemplate.opsForValue().set(SHOP_KEY, String.valueOf(--shopNumber));
            return "购买成功，当前库存剩余" + shopNumber;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            redisLock.unlock();
        }
    }
}


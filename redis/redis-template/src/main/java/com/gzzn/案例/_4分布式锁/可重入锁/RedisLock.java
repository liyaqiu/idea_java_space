package com.gzzn.案例._4分布式锁.可重入锁;

import lombok.Builder;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Arrays;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

@Builder
public class RedisLock {

    private StringRedisTemplate redisTemplate;
    private String key;
    private String hKey;
    private int expireTime;

    public boolean lock() {
        String script =
                "if redis.call('exists',KEYS[1]) == 0 or redis.call('hexists',KEYS[1],ARGV[1]) == 1 then " +
                        "redis.call('hincrby',KEYS[1],ARGV[1],1) " +
                "redis.call('expire',KEYS[1],ARGV[2]) " +
                        "return 1 " +
                "else " +
                        "return 0 " +
                 "end";
        while (!(boolean) redisTemplate.execute(new DefaultRedisScript(script, Boolean.class), Arrays.asList(key), hKey, String.valueOf(expireTime))) {
            try {
                TimeUnit.MILLISECONDS.sleep(2000);
                System.out.println("重试加锁");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        //开启自动续期
        resetExpireTime();
        return true;
    }

    public void unlock() {
        String script =
                "if redis.call('hexists',KEYS[1],ARGV[1]) == 0 then " +
                        "return nil " +
                "elseif redis.call('hincrby',KEYS[1],ARGV[1],-1) == 0 then " +
                        "return redis.call('del',KEYS[1]) " +
                "else " +
                        "return 0 " +
                "end";
        redisTemplate.execute(new DefaultRedisScript(script, Long.class), Arrays.asList(key), hKey);
    }

    private void resetExpireTime() {
        String script =
                "if redis.call('HEXISTS',KEYS[1],ARGV[1]) == 1 then " +
                        "return redis.call('expire',KEYS[1],ARGV[2]) " +
                "else " +
                        "return 0 " +
                "end";
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                if((boolean) redisTemplate.execute(new DefaultRedisScript(script, Boolean.class), Arrays.asList(key), hKey,String.valueOf(expireTime))){
                    //如果能成功，正面Key还存在，可以继续下一轮的自动续期
                    resetExpireTime();
                }
            }
        }, this.expireTime * 1000 / 3);
    }
}

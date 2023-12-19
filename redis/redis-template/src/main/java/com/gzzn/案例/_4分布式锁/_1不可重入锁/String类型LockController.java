package com.gzzn.案例._4分布式锁._1不可重入锁;

import cn.hutool.core.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * 可重入锁
 */
@RestController
@RequestMapping("/StringLock")
public class String类型LockController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    private final String SHOP_KEY = "SHOP_KEY";

    private final String LOCK_KEY = "LOCK_KEY";

    @GetMapping("test01")
    public String test01() {
        String lockValue = IdUtil.randomUUID() + ":" + Thread.currentThread().getId();
        try {
            // 加锁 start
            while (!redisTemplate.opsForValue().setIfAbsent(LOCK_KEY, lockValue, 10L, TimeUnit.SECONDS)) {
                TimeUnit.MILLISECONDS.sleep(2000);
                System.out.println("重试加锁");
            }
            // 加锁 end

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
            /*String value = redisTemplate.opsForValue().get(LOCK_KEY);
            if(value!=null&&value.equals(lockValue)){
                System.out.println("删除锁");
                redisTemplate.delete(LOCK_KEY);
            }*/


            /*
            if redis.call('get',KEYS[1]) == ARGV[1] then
                return redis.call('del',KEYS[1])
            else
                return 0
            end
            * */

            //解锁 start
            //只能删除自己的锁，利用lua脚本保证多命令的原子性
            String script =
                    "if redis.call('get',KEYS[1]) == ARGV[1] then " +
                            "return redis.call('del',KEYS[1]) " +
                            "else " +
                            "return 0 " +
                            "end ";
            redisTemplate.execute(new DefaultRedisScript(script, Boolean.class), Arrays.asList(LOCK_KEY), lockValue);
            //解锁 end
        }
    }
}

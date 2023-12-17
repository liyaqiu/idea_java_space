package com.gzzn.案例._3布隆过滤器_缓存穿透.reids;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

@Component
public class RedisUserBloomFilterManager {
    @Autowired
    private RedisTemplate redisTemplate;
    private final String PREFIX = "user-id:";

    @PostConstruct
    public void initData(){
        List<String> keys = Arrays.asList(PREFIX+"01",PREFIX+"02",PREFIX+"03");
        for (String key : keys) {
            long index = getBitmapIndex(key);
            //设置bitmap的某个位置为1
            redisTemplate.opsForValue().setBit(key,index,true);
        }
    }

    /**
     * 通过key计算出布隆过滤器下标
     * */
    public long getBitmapIndex(String key){
        int hashCode = Math.abs(key.hashCode());
        long index = (long) (hashCode % Math.pow(2,32));
        return index;
    }

    public boolean checkKeyWithBloomFilter(String key){
        long index = getBitmapIndex(PREFIX+key);
        boolean bl = redisTemplate.opsForValue().getBit(PREFIX + key, index);
        return bl;
    }
}

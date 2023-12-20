package com.gzzn.案例._3布隆过滤器_缓存穿透.guava;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

@Component
public class GuavaUserBloomFilterManager {

    //样本数大小
    private int dataSize = 1_000_000;
    //误判率
    private final double fpp = 0.3d;
    private BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.defaultCharset()),dataSize,fpp);

    @PostConstruct
    public void initData(){
        List<String> userIds = Arrays.asList("01","02","03");
        for (String userId : userIds) {
            bloomFilter.put(userId);
        }
    }

    public boolean checkKeyWithBloomFilter(String key){
        return bloomFilter.mightContain(key);
    }
}

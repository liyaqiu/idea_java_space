package com.a.b.controller;

import lombok.extern.slf4j.Slf4j;
import net.oschina.j2cache.CacheChannel;
import net.oschina.j2cache.CacheObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author eric
 * @date 2022/9/23 21:23
 **/
@RestController
@RequestMapping("/cache")
@Slf4j
public class TestController {

    private String key = "myKey";
    private String region="rx";
    @Autowired
    private CacheChannel cacheChannel;

    @GetMapping("/getInfos")
    public List<String> getInfos(){
        /**
         * 从缓存中获取数据，需要指定区域region和key
         * 以下几种情况
         *  情况1：1级和2级都不存在，则会进行1级和2级缓冲同步
         *  情况2：1级缓存存在，2级不存在，则不会进行2级缓存同步，大部分情况下2级缓存是不会丢失的
         *  情况3：1级缓存不存在，2级缓存存在，则会进行1级缓存同步
         * */
        CacheObject cacheObject = cacheChannel.get(region, key);
        if(cacheObject.getValue() == null){
            //缓存中没有找到，查询数据库获得
            List<String> data = new ArrayList<String>();
            data.add("info1");
            data.add("info2");
            //放入缓存
            cacheChannel.set(region,key,data);
            return data;
        }
        return (List<String>) cacheObject.getValue();
    }

    //清理指定缓存
    @GetMapping("/evict")
    public String evict(){
        cacheChannel.evict(region,key);
        return "evict success";
    }

    //清理指定区域中的所有缓存
    @GetMapping("/clear")
    public String clear(){
        cacheChannel.clear(region);
        return "clear success";
    }

    //检查指定的缓存数据是否存在
    @GetMapping("/exists")
    public boolean exists(){
        boolean exists = cacheChannel.exists(region, key);
        return exists;
    }

    //检查指定的缓存数据是从哪一级缓存获取到的
    @GetMapping("/check")
    public String check(){
        int level = cacheChannel.check(region, key);
        return "level:" + level;
    }
}

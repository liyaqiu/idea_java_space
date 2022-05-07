package com.mycache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lyq
 * @date 2022/3/8 11:23
 * 官方文档 https://docs.spring.io/spring-boot/docs/2.6.4/reference/htmlsingle/#io.caching.provider.caffeine
 * @EnableCaching
 * public class SpringLauncher {
 */
@RestController
@RequestMapping("/cache")
@Slf4j
public class CacheController {

    @Autowired
    ICacheService cacheService;

    @Autowired
    CacheManager cacheManager;

    @GetMapping("/test1/{id}")
    public String test1(@PathVariable String id){

        log.info("test1   {}",id);
        log.info("cacheService   {}",cacheService.getClass());
        Cache cache = cacheManager.getCache("userCache");
        System.out.println(cache.getClass());
        Cache.ValueWrapper wrapper = cache.get("1");
        if(wrapper!=null){
            CacheUser cacheUser = (CacheUser) wrapper.get();
            System.out.println(cacheUser);
        }
        cacheService.selectById(id);
        return "success";
    }

    @GetMapping("/test2")
    public void test2(){
        log.info("length:{}",cacheManager.getCacheNames().size());
        for (String cacheName : cacheManager.getCacheNames()) {
            log.info("cacheName {}",cacheName);
        }
    }
}

package com.mycache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/test1/{id}")
    public CacheUser test1(@PathVariable String id){
        log.info("test1   {}",id);
        return cacheService.selectById(id);
    }

    @GetMapping("/test2/{id}")
    public CacheUser test2(@PathVariable String id){
        log.info("test2   {}",id);
        return cacheService.selectById2(id);
    }

}

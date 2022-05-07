package com.mycache;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author lyq
 * @date 2022/3/10 18:34
 */
@Service
@Slf4j
public class CacheService implements ICacheService{


    @Cacheable(value = "userCache",key = "#id") //放和取
    //@CachePut(value = "userCache",key = "#id") //放
    public CacheUser selectById(String id){
        log.info("CacheService CacheUser:{}",id);
        return new CacheUser(id, "name"+id, "age"+id);
    }
}

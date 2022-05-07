package com.mycache;

import com.alicp.jetcache.Cache;
import com.alicp.jetcache.anno.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author lyq
 * @date 2022/3/10 18:34
 */
@Service
@Slf4j
public class CacheService implements ICacheService{

    @CreateCache(area = "zms",name = "jetCache_",expire = 10,cacheType = CacheType.REMOTE) //zms_jetCache_1
    //@CreateCache(area = "default",name = "jetCache_",expire = 10,cacheType = CacheType.LOCAL) //default_jetCache_1
    //@CreateCache(area = "zms",name = "jetCache_",expire = 10,cacheType = CacheType.BOTH)
    Cache<String,CacheUser> jetCache;

    @Override
    public CacheUser selectById(String id){
        CacheUser cacheUser = jetCache.get(id);
        if (cacheUser == null) {
            log.info("CacheService CacheUser:{}",id);
            jetCache.put(id,  new CacheUser(id, "name"+id, "age"+id));
        }
        return cacheUser;
    }

    @Override
    @Cached(area = "zms",name = "jetCache_",key = "#id",expire = 10,cacheType = CacheType.REMOTE)
    //@CacheRefresh(refresh = 5) //每5秒自动刷缓存
    public CacheUser selectById2(String id) {
        CacheUser cacheUser = jetCache.get(id);
        if (cacheUser == null) {
            log.info("CacheService CacheUser:{}",id);
            jetCache.put(id,  new CacheUser(id, "name"+id, "age"+id));
        }
        return cacheUser;
    }

    @Override
    @CacheUpdate(area = "zms",name = "jetCache_",key = "#user.id",value = "#user")
    public void updateUser(CacheUser user) {

    }

    @Override
    @CacheInvalidate(area = "zms",name = "jetCache_",key = "#id")
    public void deleteUser(String id) {

    }
}

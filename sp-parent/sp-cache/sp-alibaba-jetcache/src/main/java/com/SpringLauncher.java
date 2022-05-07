package com;

import com.alicp.jetcache.anno.config.EnableCreateCacheAnnotation;
import com.alicp.jetcache.anno.config.EnableMethodCache;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author lyq
 * @date 2021/12/4 12:34
 * alibaba jetcache缓存支持远程和本地缓存，形成多级缓存公用  扩展j2cache
 *
 */
@SpringBootApplication
@EnableCreateCacheAnnotation
@EnableMethodCache(basePackages = {"com.sp.mycache"})//启用方法注解功能
public class SpringLauncher {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringLauncher.class, args);
    }


}

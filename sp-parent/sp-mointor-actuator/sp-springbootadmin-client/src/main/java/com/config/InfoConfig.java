package com.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.context.annotation.Configuration;

/**
 * @author eric
 * @date 2022/3/15 15:01
 **/
@Configuration(proxyBeanMethods = false)
@Slf4j
public class InfoConfig implements InfoContributor {


    @Override
    public void contribute(Info.Builder builder) {
        log.info("InfoConfig");
        builder.withDetail("dynamic time", System.currentTimeMillis());
    }
}

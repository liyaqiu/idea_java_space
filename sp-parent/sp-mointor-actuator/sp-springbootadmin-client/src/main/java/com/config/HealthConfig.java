package com.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.context.annotation.Configuration;

/**
 * @author eric
 * @date 2022/3/15 15:01
 **/
@Configuration(value = "myHealth",proxyBeanMethods = false)
@Slf4j
public class HealthConfig extends AbstractHealthIndicator {


    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        log.info("HealthConfig");
        builder.withDetail("dynamic time", System.currentTimeMillis()).up();//down();
    }
}

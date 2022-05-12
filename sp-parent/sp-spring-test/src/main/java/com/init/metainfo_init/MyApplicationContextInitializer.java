package com.init.metainfo_init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * @author eric
 * @date 2022/3/18 15:51
 *  需要 spring.factories 进行注册
 **/
@Slf4j
public class MyApplicationContextInitializer implements ApplicationContextInitializer, Ordered {
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        log.info("{}","MyApplicationContextInitializer.initialize");
    }

    @Override
    public int getOrder() {
        return Integer.MAX_VALUE;
    }
}

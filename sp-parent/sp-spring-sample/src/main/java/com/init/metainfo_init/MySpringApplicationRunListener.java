package com.init.metainfo_init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author eric
 * @date 2022/3/18 15:52
 * 需要 spring.factories 进行注册
 **/
@Slf4j
public class MySpringApplicationRunListener implements SpringApplicationRunListener {




    private final SpringApplication application;
    private final String[] args;

    public MySpringApplicationRunListener(SpringApplication application, String[] args) {
        this.application = application;
        this.args = args;
    }

    @Override
    public void starting() {
        log.info("MySpringApplicationRunListener.starting");
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        log.info("MySpringApplicationRunListener.environmentPrepared");
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        log.info("MySpringApplicationRunListener.contextPrepared");
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        log.info("MySpringApplicationRunListener.contextLoaded");
    }

    @Override
    public void started(ConfigurableApplicationContext context) {
        log.info("MySpringApplicationRunListener.started");
    }

    @Override
    public void running(ConfigurableApplicationContext context) {
        log.info("MySpringApplicationRunListener.running");
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        log.info("MySpringApplicationRunListener.failed");
    }
}

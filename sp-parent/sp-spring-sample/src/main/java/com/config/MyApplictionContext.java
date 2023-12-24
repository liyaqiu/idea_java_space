package com.config;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author lyq
 * @date 2022/2/22 4:02
 */
@Configuration
@Slf4j
@ComponentScan("cn.bean")
public class MyApplictionContext implements ApplicationContextAware {


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        //log.info("spring bean 初始化完成。。。。");
    }


}

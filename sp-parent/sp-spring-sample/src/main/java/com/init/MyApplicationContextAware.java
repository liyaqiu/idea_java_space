package com.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * @author lyq
 * @date 2022/2/22 4:02
 */
@Configuration
@Slf4j
public class MyApplicationContextAware implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("MyApplicationContextAware 所有bean创建完成之后返回ApplicationContext ");
        TestBean bean = applicationContext.getBean(TestBean.class);
        log.info("bean:{}",bean);
        MyInitializingBean myInitializingBean = applicationContext.getBean(MyInitializingBean.class);
        log.info("MyInitializingBean:{}",myInitializingBean);
    }
}

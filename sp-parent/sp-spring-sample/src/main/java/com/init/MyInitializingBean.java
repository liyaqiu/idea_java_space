package com.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @author lyq
 * @date 2022/2/21 2:39
 */
@Component
@Slf4j
public class MyInitializingBean implements InitializingBean {

    public MyInitializingBean(){
        super();
        log.info("MyInitializingBean....");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("MyInitializingBean afterPropertiesSet。。。。");
    }
}

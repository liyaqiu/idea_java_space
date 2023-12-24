package com.init;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author lyq
 * @date 2022/3/7 9:54
 */
@Component
@Slf4j
public class TestBean {
    public TestBean(){
        super();
        log.info("TestBean....");
    }
}

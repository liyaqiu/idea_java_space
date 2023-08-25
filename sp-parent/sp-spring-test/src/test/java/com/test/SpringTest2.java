package com.test;

import com.SpringLauncher;
import com.config.MyconfigYAML;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author lyq
 * @date 2022/2/27 1:28
 */
@SpringBootTest(classes = SpringLauncher.class)//默认找同包下的SpringLauncher
@Slf4j
public class SpringTest2 {

    @Autowired
    MyconfigYAML myconfigYAML;

    //import org.junit.jupiter.api.Test;
    @Test
    public void test0(){
        log.info("hello  {}",myconfigYAML);
    }
}

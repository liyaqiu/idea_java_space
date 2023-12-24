package com.test;

import com.SpringLauncher;
import com.config.MyconfigProperties;
import com.config.MyconfigYAML;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * @author lyq
 * @date 2022/2/27 1:28
 */

@RunWith(SpringJUnit4ClassRunner.class) //让junit去找spring集成的test
//@ContextConfiguration(classes = SpringLauncher.class) //指定配置类
@SpringBootTest(classes = SpringLauncher.class)//指定配置类，默认找同包下的SpringLauncher
@Slf4j
public class SpringTest {

    @Autowired
    MyconfigProperties myconfigProperties;
    @Autowired
    MyconfigYAML myconfigYAML;

    //import org.junit.Test;
    @Test
    public void test0(){
        log.info("hello  {}",myconfigProperties);
        log.info("hello  {}",myconfigYAML);
    }
}

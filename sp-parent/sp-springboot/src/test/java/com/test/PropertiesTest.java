package com.test;

import com.Launcher;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import com.test.config.ConfigTest;

/**
 * @author lyq
 * @date 2021/12/4 15:31
 */
@SpringBootTest(classes = Launcher.class,properties = {"testkey=123"},args = {"--testkey=456"})
@Import({ConfigTest.class})
@Slf4j
public class PropertiesTest {

    @Autowired
    @Qualifier("liyaqiu")
    String string;
    @Value("${testkey}")
    String testkey;

    @Test
    public void test0(){
        log.info("testkey {}",testkey);
         log.info("string {}",string);
    }
}

package com;

import com.config.TestConfig;
import com.entity.User;
import com.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lyq
 * @date 2022/3/8 17:19
 */
@SpringBootTest
//@Transactional
//@Rollback(value = true)
@Slf4j
public class Test1 {

    @Autowired
    UserService userService;
    @Autowired
    TestConfig testConfig;

    @Test
    public void test0() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i <10 ; i++) {
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i <5000000 ; i++) {
                        User user = new User();
                        user.setId(UUID.randomUUID().toString());
                        user.setName(i+"luyaqiu");
                        user.setAge(i+15);
                        userService.save(user);
                    }
                }
            });
        }

        Thread.sleep(Integer.MAX_VALUE);

        System.out.println("end....");
    }

    @Test
    public void test1(){
        log.info("testConfig {}",testConfig);
    }
}






















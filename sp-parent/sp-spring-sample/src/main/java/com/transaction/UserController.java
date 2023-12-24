package com.transaction;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lyq
 * @date 2022/3/3 3:06
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    UserServiceImpl userService;

    @GetMapping("/test1")
    public void test1(){
        log.info("test.controller test1 ");
        userService.test0();
    }
    @GetMapping("/test2")
    @Transactional//事物传播
    public void test2(){
        log.info("test.controller test2");
        userService.test0();
    }

    @GetMapping("/test3")
    public void test3(){
        log.info("test.controller test3 {}",userService.getClass());
        userService.test1();
    }

}

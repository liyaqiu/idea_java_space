package com.mybatisplus.jdkproxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/mybatis")
@Slf4j
public class MybatisController {

    @Autowired //mybatis用了动态代理来调用映射文件中的方法
    UserNameMapper2 userNameMapper2;


    @GetMapping("/test")
    public void test() {
        log.info("userMapper  {}", userNameMapper2.getClass());
        log.info("test1  {}", userNameMapper2.delete("1000"));
    }

}

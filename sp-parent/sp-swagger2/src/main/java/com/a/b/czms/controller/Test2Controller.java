package com.a.b.czms.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author eric
 * @date 2022/3/23 16:30
 **/
@RestController
@Slf4j
public class Test2Controller {

    @GetMapping("/test2")
    public void test(){
        log.info("{}","test");
    }
}

package com.gzzn.test.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lyq
 * @date 2021/12/4 12:33
 */
@RestController
@Slf4j
public class UserController {

    @GetMapping("/test")
    public String test2(){
        log.info("PostMapping");
        return "你好";
    }

}



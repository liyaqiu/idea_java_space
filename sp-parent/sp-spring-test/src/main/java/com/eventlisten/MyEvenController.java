package com.eventlisten;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lyq
 * @date 2021/12/4 12:33
 */
@RestController
@RequestMapping("/event")
@Slf4j
public class MyEvenController {


    @Autowired
    ApplicationContext applicationContext;

    @GetMapping("/test1")
    public void test1(){
        applicationContext.publishEvent(new MyEvent(new EventObject("liyaqiu", 31)));
        log.info("完成......");
    }

}

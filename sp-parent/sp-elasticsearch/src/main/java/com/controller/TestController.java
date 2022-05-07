package com.controller;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lyq
 * @date 2022/1/18 5:56
 */
@RestController
@Slf4j
public class TestController {

    @Autowired
    RestHighLevelClient client;

    @GetMapping("/test0")
    public void test0(){
        log.info("client:{}",client);

    }

    public static void main(String[] args) {
        int i = 0;
        if(i==0&&i!=5){

        }
    }

}

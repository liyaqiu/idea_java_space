package com.controller;

import com.config.MyConfig;
import com.config.ShareConfig;
import com.entity.UserEntity;
import com.feign.client.UserClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author lyq
 * @date 2022/1/6 11:36
 */
@RestController
@Slf4j
public class UserController {

    @Autowired
    RestTemplate restTemplate;
    @Autowired
    MyConfig myConfig;
    @Autowired
    ShareConfig shareConfig;
    @Autowired
    UserClient userClient;

    @GetMapping("/test")
    public String test0(){
        log.debug("<---{}--->你好111！！！",restTemplate);
        restTemplate.getForEntity("http://sp-service/service-test0",String.class);
        return "hello!!!";
    }

    @GetMapping("/test2")
    public String test2(){
        log.debug("name:{},age:{}",myConfig.getName(),myConfig.getAge());
        log.debug("shareName:{},shareAge:{}",shareConfig.getShareName(),shareConfig.getShareAge());
        return "name:"+myConfig.getName()+",age:"+myConfig.getAge();
    }
    @GetMapping("/test3/{id}")
    public String test3(@PathVariable String id, @RequestHeader(value = "tou",required = true)String tou){
        return tou;
    }

    @GetMapping("/test41/{id}")
    public UserEntity test4(@PathVariable String id){
        UserEntity userEntity = userClient.selectUser(id);
        log.debug("用户信息：{}", userEntity);
        return userEntity;
    }


}

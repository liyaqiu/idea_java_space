package com.controller;

import com.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lyq
 * @date 2022/1/6 11:43
 */
@RestController
@Slf4j
public class UserController {
    @GetMapping("/service-test0")
    public String test0(){
        log.debug("{}","service-test0");
        return "service-test0!!!";
    }

    @GetMapping("/selectUser/{id}")
    public UserEntity selectUser(@PathVariable String id){
        UserEntity userEntity = new UserEntity();
        userEntity.setName("李雅秋");
        userEntity.setAge("28");
        log.debug("id:{}",id);
        return userEntity;
    }

}

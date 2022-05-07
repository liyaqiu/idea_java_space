package com.controller;

import com.common.Result;
import com.entity.User;
import com.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lyq
 * @date 2021/12/4 12:33
 */
@RestController
@Slf4j
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    ApplicationContext applicationContext;


    @GetMapping("/test")
    public Result test0(){
       /*if(true){
           throw new RuntimeException("异常测试。。。");
       }*/
        log.debug("{}","你好！！！");
       return new Result(Result.OK,"执行成功11111",true,new User());
    }

    @PostMapping("/posttest")
    public Result posttest(@RequestBody User user){
        log.debug("你好 {}",user);
        return new Result(Result.OK,"执行成功11111",true,new User());
    }


    @GetMapping("/test2")
    public String test2(){
        log.info("test2执行完成......");
        return "helloworld";
    }


}

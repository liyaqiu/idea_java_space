package com.controller;

import com.common.Result;
import com.entity.User;
import com.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

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
    public Result test1(HttpServletResponse response,JSRequestLineInfo requestLineInfo){
        log.debug("get requestLineInfo:{}",requestLineInfo);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        response.setHeader("server", "server");
        response.setHeader("token", "servertoken");

        return new Result(Result.OK,"执行成功",true,null);
    }


    //@GetMapping("/test")
    @PostMapping("/test")
    //@PutMapping("/test")
    //@DeleteMapping("/test")
    public Result test0(HttpServletResponse response,JSRequestLineInfo requestLineInfo ,@RequestBody JSRequestBodyUser requestBodyUser){
        log.debug("post requestLineInfo:{}",requestLineInfo);
        log.debug("post requestBodyUser:{}",requestBodyUser);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        response.setHeader("server", "server");
        response.setHeader("token", "servertoken");

       return new Result(Result.OK,"执行成功",true,requestBodyUser);
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

package com.controller;

import cn.hutool.json.JSONUtil;
import com.common.Result;
import com.config获取其他配置.MyConfig;
import com.entity.User;
import com.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.Map;

/**
 * @author lyq
 * @date 2021/12/4 12:33
 */
@RestController
@Slf4j
public class UserController  {

    @Autowired
    UserService userService;
    @Autowired
    ApplicationContext applicationContext;


    /*@GetMapping("/test")
    public void test_1(){}

    @PostMapping("/test")
    public void test_2(){}

    @PutMapping("/test")
    public void test_3(){}

    @DeleteMapping("/test")
    public void test_4(){}*/


    @PostMapping("/test/test11")
    public void test_2(@RequestBody Map<String,Object> map,HttpServletResponse response) throws IOException {
        log.error("获取的对象 {}",JSONUtil.toJsonStr(map));
        response.sendRedirect("http://192.168.88.111:8888/hello2.html");
    }

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
    //@PostMapping("/test")
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
        //response.setStatus(404);
       return new Result(Result.OK,"执行成功",true,requestBodyUser);
    }

    @PostMapping("/posttest")
    public Result posttest(@RequestBody User user){
        log.debug("你好 {}",user);
        return new Result(Result.OK,"执行成功11111",true,new User());
    }



    @GetMapping("/test2")
    @Transactional
    public String test2(){
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization(){
            //事务提交后才执行
            @Override
            public void afterCommit() {
                System.out.println("事务提交后才执行");
            }
        });
        log.info("test2执行完成......");
        User user = new User();
        user.setName("eric");
        user.setAge(111);
        user.setFamilies(null);
        userService.save(user);

        System.out.println(userService.getById(user.getId()));
        return "helloworld";
    }

    public static void main(String[] args) {

        System.out.println(Person.B);
    }


}

package com.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;


@RestController
@RequestMapping("/demo4")
@Slf4j
public class TestController4 {


    //http://192.168.0.110:8080/demo4/test1?date=2020/10/10
    @GetMapping("/test1")
    public void test1(Date date) {
        log.info("test1 :{}",date);
    }

    @GetMapping("/test2")
    public void test1(HttpServletRequest request, HttpServletResponse response, HttpSession session) throws InterruptedException {
        log.info("test1 request:{}",request.getRequestURI());
        log.info("test1 request:{}",request.hashCode());
        log.info("test1 response:{}",response.hashCode());
        log.info("test1 session:{}",session.hashCode());
        Thread.sleep(10000);
    }

    @GetMapping("/test3")
    public void test3(@RequestHeader("User-Agent") String useragent,@RequestHeader("Cookie") String cookie,@CookieValue("sentinel_dashboard_cookie") String sentiVal){
        log.info("User-Agent:{}",useragent);
        log.info("sentinel_dashboard_cookie:{}",sentiVal);
        log.info("cookie:{}",cookie);
    }




}

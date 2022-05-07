package com.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lyq
 * @date 2022/1/6 11:36
 */
@RestController
@RequestMapping("/tt")
@Slf4j
@RefreshScope
public class Test1Controller {


    @Value("${myconfig.name:liguangliang}")
    private String name;
    @Value("${myconfig.age:100}")
    private String age;
    @Autowired
    TestService testService;

    @GetMapping("/test1")
    public String test1(){
        log.debug("name:{},age:{}",name,age);
        testService.testService();
        return "name:"+name+",age:"+age;
    }
    @GetMapping("/test2")
    public String test2(){
        testService.testService();
        log.debug("name:{},age:{}",name,age);
        return "name:"+name+",age:"+age;
    }


    @GetMapping("/test3")
    public String test3(){
        testService.testService();
        log.debug("name:{},age:{}",name,age);
        return "name:"+name+",age:"+age;
    }

    /**
     * 授权规则
     * */
    @GetMapping("/test4")
    public String test4(){
        log.debug("name:{},age:{}",name,age);
        return "name:"+name+",age:"+age;
    }



    @Service
    public static class TestService{
        //web-context-unify: false #默认会将所有的controller整个到同一个链路，关闭上下文链路
        //对链路做流量控制
        @SentinelResource("testService")
        public void testService(){
            log.info("{}","testService");
        }

        /**
         * 热点参数限流，不支持spring mvc，也就是（controller声明的不生效）需要增加注解参数@SentinelResource
         * 底层代码需要用args来判断
         *  */
        @SentinelResource("redian")
        public void test0(String arg1,String arg2){

        }

    }


}

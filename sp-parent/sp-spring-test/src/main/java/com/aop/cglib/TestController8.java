package com.aop.cglib;

import com.aop.cglib.proxyobj.TargetImpl3;
import com.aop.cglib.proxyobj2.TargetImpl4;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("/cglib/aspect")
@Slf4j
public class TestController8 {

    @Autowired

    TargetImpl3 targetImpl3;

    @Autowired
    TargetImpl4 targetImpl4;

    @GetMapping("/test1")
    public void test1(HttpServletRequest request, @RequestParam(value = "name",defaultValue = "liyaqiu") String name) {

        //targetImpl3.sayHello();
        targetImpl3.sayHello("liyaqiu", 12);
        log.info("targetImpl3 {}   name {}",targetImpl3.getClass(),name);


//        targetImpl4.sayHello();
//        log.info("targetImpl4 {}",targetImpl4.getClass());
        log.info("cglib test1");
    }





}

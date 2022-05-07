package com.aop.jdk;

import com.aop.jdk.proxyobj.Target;
import com.aop.jdk.proxyobj.Target1Impl;
import com.aop.jdk.proxyobj2.Target2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;


@RestController
@RequestMapping("/aspect")
@Slf4j
public class TestController7 {

    @Autowired
    Target target;
    @Autowired
    Target2 target2;

    @GetMapping("/test1")
    public void test1() {
        Target target3 = new Target1Impl();
        target.sayHello();
        log.info("target {}",target.getClass());
        target2.sayHello();
        log.info("target2 {}",target2.getClass());
        log.info("jdk test1");
    }

    @GetMapping("/test2")
    public void test2() {
        target2.sayHello();
        log.info("test2");
    }


}

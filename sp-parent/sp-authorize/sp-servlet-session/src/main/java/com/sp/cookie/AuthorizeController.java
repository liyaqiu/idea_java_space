package com.sp.cookie;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @author eric
 * @date 2022/3/19 10:11
 **/
@RequestMapping("/authorize")
@RestController
@Slf4j
public class AuthorizeController {
    @GetMapping("/cookie")
    public void test1(HttpServletResponse response){
        Cookie cookie = new Cookie("token", "123456");
        cookie.setDomain("aaa1.com");
        //cookie.setDomain("www.aaa1.com");
        //cookie.setDomain("abc.aaa1.com");
        response.addCookie(cookie);
        Cookie cookie1 = new Cookie("token", "123456");
        cookie1.setDomain("abc.aaa1.com");
        response.addCookie(cookie1);
        log.info("cookie {}");
    }

    @GetMapping("/test2")
    public void test2(@CookieValue(value = "token",required = false) String token){
        log.info("test2 {}",token);
    }
}

package com.intercepter;

import com.bean.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Random;
import java.util.RandomAccess;
import java.util.UUID;


@RestController
//@RequestMapping("/demo5")
@Slf4j
public class TestController5 {



    //利用ngxin代理，做集群认证
    //创建cookie
    @GetMapping("/login")
    public void nginxTest1(HttpServletRequest request,HttpServletResponse response) {
        //1从数据库判断
        //jdbc.query("select * from user where = username ='张三 and passwd='123456'")
        //2生成token，一份返回客户端，一份存redis
        String token = UUID.randomUUID().toString();
        //存redis
        //redis.set(token,"用户名")
        Cookie cookie = new Cookie("token", token);//创建新cookie
        cookie.setMaxAge(-1);
        cookie.setPath("/");
        response.addCookie(cookie);//将cookie返回给客户端
        log.info("login");
    }

    @GetMapping("/user/auth")
    public String ngxinTest2(@CookieValue(value = "token",required = false) String token) {
        log.info("user auth token:{}",token);
        return "可以进行访问";
    }

    //做session缓存
    @GetMapping("/sessionlogin")
    public void sessionTest1(HttpServletRequest request,HttpServletResponse response) {
        //1从数据库判断
        //int count = jdbc.query("select * from user where = username ='张三 and passwd='123456'")
        //if(count != 1){
            request.getSession().setAttribute("username", "liyaqiu");
       // }
        log.info("sessionlogin");
    }

}

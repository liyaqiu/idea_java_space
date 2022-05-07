package com.sp.session_authorize;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author eric
 * @date 2022/3/19 12:45
 **/
@Slf4j
@RestController
public class SessionController {

    @Autowired
    SessionDatabase database;

    @GetMapping("/login")
    public String login(SessionUser user, HttpServletRequest request){
        log.info("login {}",user);
        SessionUser sessionUser = database.getUser(user.getUserName());
        if(sessionUser==null){
            return "用户名不存在";
        }
        if(!sessionUser.getPassword().equals(user.getPassword())){
            return "用户名不存在 或 密码错误";
        }

        request.getSession().setAttribute("user", sessionUser);

        return "登陆成功";
    }


    @GetMapping("/session/resource1")
    public String resource1(){
        log.info("resource1");
        return "资源1访问成功";
    }

    @GetMapping("/session/resource2")
    public String resource2(){
        log.info("resource2");
        return "资源2访问成功";
    }
}

package com.sp.spring_security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author eric
 * @date 2022/3/19 12:45
 **/
@Slf4j
@RestController
public class SecurityController {

/*
    @GetMapping("/security/helloworld")
    @PreAuthorize("hasAuthority('p2')")//权限
    @Secured({"ROLE_admin"})//角色
    public String helloworld(){
        log.info("helloworld");
        return "helloworld";
    }
*/


    @GetMapping("/security/resource1")
    public String resource1(){
        log.info("resource1");
        return "资源1访问成功";
    }

    @GetMapping("/security/resource2")
    public String resource2(){
        log.info("resource2");
        return "资源2访问成功";
    }

    @Autowired(required = false)
    CsrfTokenRepository csrfTokenRepository;

    @RequestMapping("/loginpage")
    public String loginpage(HttpServletRequest request, HttpServletResponse response){

        log.info("loginpage");

        String htmlStr = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "login\n" +
                "<form action=\"/submitlogin\" method=\"post\" enctype=\"application/x-www-form-urlencoded\">\n" +
                "    用户<input type=\"text\" name=\"name\"><br>\n" +
                "    密码<input type=\"text\" name=\"pass\"><br>\n" +
                "    <input type=\"checkbox\" name=\"remember-me\">\n" +
                "    <input type=\"submit\" value=\"登录\">\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>";
        return htmlStr;

       /* Map<String,String> map = new HashMap<>();
        map.put("state", "302");
        map.put("message", "no login");
        return  map;*/

       /* CsrfToken csrfToken = csrfTokenRepository.loadToken(request);
       log.info("loginpage: {} --- {}",csrfToken.getParameterName(),csrfToken.getToken() );
        String htmlStr = "<!DOCTYPE html>\n" +
                "<html lang=\"en\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <title>Title</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "login\n" +
                "<form action=\"/submitlogin\" method=\"post\" enctype=\"application/x-www-form-urlencoded\">\n" +
                "    用户<input type=\"text\" name=\"name\"><br>\n" +
                "    密码<input type=\"text\" name=\"pass\"><br>\n" +
                "    <input type=\"checkbox\" name=\"remember-me\">\n" +
                "    <input type=\"test\" name=\""+csrfToken.getParameterName()+"\" value=\""+csrfToken.getToken()+"\">\n" +
                "    <input type=\"submit\" value=\"登录\">\n" +
                "</form>\n" +
                "</body>\n" +
                "</html>";
        return htmlStr;*/
    }
    @RequestMapping("/success")
    public String success(HttpSession session){
        log.info("success");
        return getUserName()+"登陆成功";
    }


    @RequestMapping("/out")
    public String out(HttpSession session){
        log.info("out");
        return getUserName()+"退出成功";
    }

    /**
     * 获取用户名
     * */
    public String getUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(!authentication.isAuthenticated()){
            return null;
        }
        Object principal = authentication.getPrincipal();
        if(principal instanceof UserDetails){
             return  ((UserDetails)principal).getUsername();
        }
        if(principal!=null){
            return principal.toString();
        }
        return null;
    }
}

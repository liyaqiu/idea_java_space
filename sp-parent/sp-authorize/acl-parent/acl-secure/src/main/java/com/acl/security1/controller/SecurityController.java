package com.acl.security1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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

    @GetMapping("/index")
    public String index(HttpServletRequest request){
        AntPathRequestMatcher requestMatcher = new AntPathRequestMatcher("/index", "GET");
        log.info("index  match:{}",requestMatcher.matcher(request).isMatch());
        return "该用户：  "+getUserName()+"  index";
    }

    @GetMapping("/security/resource1")
    public String resource1(){
        log.info("resource1");
        return getUserName()+"资源1访问成功";
    }

    @GetMapping("/security/resource2")
    public String resource2(){
        log.info("resource2");
        return getUserName()+"资源2访问成功";
    }



    /**
     * 获取用户名
     * */
    public String getUserName(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null){
            return null;
        }
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

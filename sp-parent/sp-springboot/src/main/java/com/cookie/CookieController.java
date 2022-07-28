package com.cookie;

import com.common.Result;
import com.controller.JSRequestLineInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

/**
 * @author lyq
 * @date 2021/12/4 12:33
 */
@RestController
@Slf4j
public class CookieController {


    @GetMapping("/setCookie")
    public Result setCookie(HttpServletRequest request ,HttpServletResponse response){

        /*
            如果用http://a1.cookie1.aaa.com:9999/setCookie 发起请求
            http://aaa.com:9999/getCookie             可以获得 token1
            http://cookie1.aaa.com:9999/getCookie     可以获得 token1 token2
            http://a1.cookie1.aaa.com:9999/getCookie  可以获得 token1 token2 token3

            如果用http://cookie1.aaa.com:9999/setCookie 发起请求
            http://aaa.com:9999/getCookie             可以获得 token1
            http://cookie1.aaa.com:9999/getCookie     可以获得 token1 token2
            http://a1.cookie1.aaa.com:9999/getCookie  可以获得 token1 token2

            如果用http://aaa.com:9999/setCookie 发起请求
            http://aaa.com:9999/getCookie             可以获得 token1
            http://cookie1.aaa.com:9999/getCookie     可以获得 token1
            http://a1.cookie1.aaa.com:9999/getCookie  可以获得 token1
        */


        Cookie cookie1 = new Cookie("token1", "token1");
        cookie1.setPath("/");
        cookie1.setDomain("aaa.com");
        cookie1.setMaxAge(-1);
        response.addCookie(cookie1);

        Cookie cookie2 = new Cookie("token2", "token2");
        cookie2.setPath("/");
        cookie2.setDomain("cookie1.aaa.com");
        cookie2.setMaxAge(-1);
        response.addCookie(cookie2);


        Cookie cookie3 = new Cookie("token3", "token3");
        cookie3.setPath("/");
        cookie3.setDomain("a1.cookie1.aaa.com");
        cookie3.setMaxAge(-1);
        response.addCookie(cookie3);

        return new Result(Result.OK,"执行成功",true,null);
    }


    /*@GetMapping("/setCookie")
    public Result setCookie(HttpServletRequest request ,HttpServletResponse response){
        //log.debug("setCookie", Arrays.toString(request.getCookies()));
        Cookie cookie1 = new Cookie("cookie1", "cookie1");

        //cookie 与端口无关，同域名和同路径符合即可获取
        //同一个域下面，不可以设置其他域的cookie
        cookie1.setDomain("cookie1.aaa.com");
        //同域名，同路径才能获取到
        cookie1.setPath("/getCookie/1");
        //大于等于0，则为有效时间，时间没到关闭浏览器也不会消失，时间到了，不关闭浏览器也会消失，单位为秒
        //小于0，则关闭浏览器即消失，单位为秒
        cookie1.setMaxAge(-1);
        response.addCookie(cookie1);


        Cookie cookie2 = new Cookie("cookie2", "cookie2");
        cookie2.setPath("/getCookie/2");
        cookie2.setDomain("cookie2.aaa.com");
        response.addCookie(cookie2);


        return new Result(Result.OK,"执行成功",true,null);
    }*/
    @GetMapping("/getCookie")
    public Result getCookie(HttpServletRequest request ,HttpServletResponse response){
        if(request.getCookies()==null){
            log.debug("不存在cookie");
        }else{
            for (Cookie cookie : request.getCookies()) {
                log.debug("key:{} value:{}",cookie.getName(),cookie.getValue() );
            }
        }
        return new Result(Result.OK,"执行成功",true,null);
    }
    @GetMapping("/getCookie/1")
    public Result getCookie1(HttpServletRequest request ,HttpServletResponse response){
        if(request.getCookies()==null){
            log.debug("不存在cookie");
        }else{
            for (Cookie cookie : request.getCookies()) {
                log.debug("key:{} value:{}",cookie.getName(),cookie.getValue() );
            }
        }

        return new Result(Result.OK,"执行成功",true,null);
    }
    @GetMapping("/getCookie/2")
    public Result getCookie2(HttpServletRequest request ,HttpServletResponse response){
        for (Cookie cookie : request.getCookies()) {
            log.debug("key:{} value:{}",cookie.getName(),cookie.getValue() );
        }
        return new Result(Result.OK,"执行成功",true,null);
    }


    @GetMapping("/login")
    public Result login(HttpServletRequest request ,HttpServletResponse response){
        response.setHeader("token", "mima123456");
        return new Result(Result.OK,"执行成功",true,null);
    }

    @GetMapping("/ajax1")
    public Result ajax1(HttpServletRequest request ,HttpServletResponse response){
        System.out.println("token"+request.getHeader("token"));
        return new Result(Result.OK,"执行成功",true,null);
    }
    @GetMapping("/ajax2")
    public Result ajax2(HttpServletRequest request ,HttpServletResponse response){
        System.out.println("token"+request.getHeader("token"));
        return new Result(Result.OK,"执行成功",true,null);
    }

}

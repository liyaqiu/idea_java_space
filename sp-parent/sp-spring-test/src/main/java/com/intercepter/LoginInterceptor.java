package com.intercepter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lyq
 * @date 2022/3/1 2:56
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    //controller执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle");

        //浏览器每次请求会提交 cookie token
        /*for (Cookie cookie : request.getCookies()) {
            if("token".equals(cookie.getName())){
                String token = cookie.getName();
                //利用nginx来做集群校验
                if(redis.getKey(token)!=null){
                    return true;
                }
            }
        }*/

        //利用session来做认证
        /**
         *  servlet 会利用cookie(map(JSESSIONID,session))来维护session
         *  如果请求携带了同样的（JSESSIONID）request.getSession()会返回同一个session
         * */
        /*if(request.getSession().getAttribute("username")!=null){
            return true;
        }*/


        //response.setStatus(403);
        //throw new RuntimeException("不能访问");

        //response.getOutputStream().print("no login");
        return true;
    }

    //controller return modelAndView 执行之后 >（postHandle） > 转发到jsp之前
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle");
    }

    // controller执行完 或者 jsp解析完成 之后
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion");
    }
}

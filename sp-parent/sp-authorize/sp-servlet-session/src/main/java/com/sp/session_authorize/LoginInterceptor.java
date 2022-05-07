package com.sp.session_authorize;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author lyq
 * @date 2022/3/1 2:56
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {

    //认证拦截
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("认证拦截器 {}",request.getRequestURI());
        SessionUser user = (SessionUser) request.getSession().getAttribute("user");
        if(user==null){
            response.getWriter().print("no login");
            return false;
        }
        return true;
    }
}

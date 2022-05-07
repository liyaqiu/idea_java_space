package com.sp.session_authorize;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author lyq
 * @date 2022/3/1 2:56
 */
@Component
@Slf4j
public class AuthorizeInterceptor implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("授权拦截器 {}",request.getRequestURI());
        SessionUser user = (SessionUser) request.getSession().getAttribute("user");

        //资源判断
        if("/session/resource1".equals(request.getRequestURI())){
            //权限判断
            for (String permission : user.getPermissions()) {
                if(permission.equals("resource1Permission")){
                    return true;
                }
            }
        }

        if("/session/resource2".equals(request.getRequestURI())){
            for (String permission : user.getPermissions()) {
                if(permission.equals("resource2Permission")){
                    return true;
                }
            }
        }

        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().print("没有权限..");
        return false;
    }
}

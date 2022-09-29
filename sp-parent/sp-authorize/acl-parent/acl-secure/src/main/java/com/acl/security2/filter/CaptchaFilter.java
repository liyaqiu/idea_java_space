package com.acl.security2.filter;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author eric
 * @date 2022/9/21 16:18
 **/
@Component
public class CaptchaFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if("/login".equals(request.getRequestURI()) && "POST".equals(request.getMethod())){

            String codeId = request.getParameter("codeId");
            String code = request.getParameter("code");
            if(StringUtils.isEmpty(codeId)||StringUtils.isEmpty(code)){
                throw new ServletException("验证码不正确");
            }

            //TODO对验证码做校验
            //redis.getCode.eq
            //redis.removeCode
        }
        //放行
        filterChain.doFilter(request, response);
    }
}

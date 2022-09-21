package com.gzzn.service.acl.security.filter;

import com.gzzn.service.common.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author eric
 * @date 2022/9/21 17:36
 **/
@Slf4j
public class TokenAuthenticationFilter extends BasicAuthenticationFilter {

    public TokenAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.debug("doFilterInternal");
        String token = request.getHeader("token");

        //如果不存在token，则没登陆过，直接放行
        if(StringUtils.isEmpty(token)){
            chain.doFilter(request, response);
            return;
        }

        //如果存在token，则已经登录，解析jwtToken
        String userName = null;
        try {
            userName = JWTUtil.parseToken(token);
        } catch (Exception e) {
            throw new ServletException(e);
        }

        //把用户信息交给框架处理
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(userName,null,null));
        //放行
        chain.doFilter(request, response);
    }
}

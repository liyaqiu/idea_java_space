package com.gzzn.service.gateway.security.filter;

import cn.hutool.core.collection.SpliteratorUtil;
import com.gzzn.service.gateway.security.manager.UsernamePasswordAuthenticationManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.TestingAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatcher;
import org.springframework.security.web.server.util.matcher.ServerWebExchangeMatchers;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.function.Predicate;

/**
 * @author eric
 * @date 2022/3/24 10:33
 * 认证过滤器
 **/
@Slf4j
//@Component
public class LoginFilter implements WebFilter {

    @Autowired
    UsernamePasswordAuthenticationManager authenticationManager;

    private ServerWebExchangeMatcher matcher = ServerWebExchangeMatchers.pathMatchers(HttpMethod.POST, "/login");

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        log.debug("filter");
        Mono<Authentication> authenticate = authenticationManager.authenticate(null);
        /*Authentication authentication = authenticate.block();
        System.out.println(ReactiveSecurityContextHolder.getContext());
        ReactiveSecurityContextHolder.withAuthentication(authentication);
        ReactiveSecurityContextHolder.getContext().filter(new Predicate(){

            @Override
            public boolean test(Object o) {
                Authentication authentication1 = (Authentication) o;
                System.out.println(authentication1.getName());
                return false;
            }
        });*/
        if(true){
            throw new AccessDeniedException("123123");
        }

        return chain.filter(exchange);
    }



    /* *//**
     * 获取表单提交的数据做初步校验
     * *//*
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("attemptAuthentication");
        matcher.
        //return super.attemptAuthentication(request, response);
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //获取请求体
        try(BufferedReader reader = request.getReader()){
            while (reader.ready()) {
                System.out.println("@@@"+reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        log.info("获取表单信息并且返回给框架做用户校验 {}  {}",username,password);

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        //传过来的数据和UserDetailsService提供的数据做一个比对校验
        return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    }*/


   /* *//**
     * 登录成功
     * *//*
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        //super.successfulAuthentication(request, response, chain, authResult);
        log.info("登录成功，将用户信息以及权限存储到redis");

    }

    public String getPermissions(UserDetails userDetails){
        StringBuilder sb = new StringBuilder();
        for (GrantedAuthority authority : userDetails.getAuthorities()) {
            sb.append(authority.getAuthority()).append(",");
        }
        return sb.substring(0, sb.length() - 1);
    }

    *//**
     * 登录失败
     * *//*
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
        log.info("登录失败");

    }*/


}

class Hello{

}
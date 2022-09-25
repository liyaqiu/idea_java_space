package com.gzzn.service.gateway.security.filter;

import com.gzzn.service.gateway.security.manager.UsernamePasswordAuthenticationManager;
import com.gzzn.service.gateway.utils.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

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
//public class TokenAuthenticationFilter extends BasicAuthenticationFilter {
public class TokenAuthenticationFilter extends AuthenticationWebFilter {



    public TokenAuthenticationFilter(ReactiveAuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    /*public TokenAuthenticationFilter(AuthenticationManager authenticationManager) {
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
    }*/

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        log.info("filter");

        //把用户信息交给框架处理
        //SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(null,null,null));

        /*ServerHttpRequest request = exchange.getRequest();
        MultiValueMap<String, String> queryParams = request.getQueryParams();
        log.info("tou1:{}",request.getHeaders().get("tou1"));
        log.info("tou2:{}",request.getHeaders().get("tou2"));
        log.info("tou3:{}",request.getHeaders().get("tou3"));
        log.info("tou4:{}",request.getHeaders().get("tou4"));
        log.info("tou5:{}",request.getHeaders().get("tou5"));
        log.info("tou6:{}",request.getHeaders().get("tou6"));
        String auth = queryParams.getFirst("auth");
        if("liyaqiu".equals(auth)){
            return chain.filter(exchange);
        }
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        return exchange.getResponse().setComplete();*/

        return chain.filter(exchange);
    }
}

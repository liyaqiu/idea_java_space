package com.gzzn.service.gateway.security.filter;

import cn.hutool.json.JSONUtil;
import com.gzzn.service.gateway.utils.Res;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author eric
 * @date 2022/9/21 16:18
 **/
//@Component
@Slf4j
//public class CaptchaFilter extends OncePerRequestFilter {
public class CaptchaFilter implements WebFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {

        if(true){
            log.info("filter 验证码正确");
        }else{
            log.info("filter 验证错误");
            ServerHttpResponse response = exchange.getResponse();
            HttpHeaders headers = response.getHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Res res = Res.fail().setMessage("验证错误");

            DataBuffer buffer = response.bufferFactory().wrap(JSONUtil.toJsonStr(res).getBytes(StandardCharsets.UTF_8));
            return response.writeWith(Mono.just(buffer));
        }
        return chain.filter(exchange);
    }

    /*@Override
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
    }*/
}

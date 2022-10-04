package com.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 可以实现一些业务上的拦截
 * 过滤器优先级是 值相同情况下 默认过滤器 > 路由过滤器 > 全局过滤器
 * 排序的值是从2开始
 * @author lyq
 * @date 2022/1/11 3:35
 */
@Component
@Slf4j
public class AuthorizeFilter implements GlobalFilter,Ordered{

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        //写request header方式
        //方法一 设置为可修改的
            HttpHeaders headers = exchange.getRequest().getHeaders();
            headers= HttpHeaders.writableHttpHeaders(headers);
            //设置请求头
            headers.set("username","lyq");
        //方法二 bulid
            //exchange.getRequest().mutate().header("username","eric").build();

        ServerHttpRequest request = exchange.getRequest();
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
        return exchange.getResponse().setComplete();
    }
    /**
     * 排序的值是从2开始
     * */
    @Override
    public int getOrder() {
        return 2;//1 0 -1
    }

}

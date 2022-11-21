package com.gzzn.service.gateway.security.filter;

import cn.hutool.json.JSONUtil;
import com.gzzn.service.gateway.security.config.WhiteList;
import com.gzzn.service.gateway.utils.JWTUtil;
import com.gzzn.service.utils.Res;
import com.gzzn.service.utils.ResCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @author eric
 * @date 2022/9/28 15:08
 **/
@Component
@Slf4j
public class JwtParseFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        log.debug("filter");
        HttpHeaders headers = exchange.getRequest().getHeaders();
        String token = headers.getFirst("token");




        //如果token不存在则放行，或者是在白名单也放行
        /*if(token==null || WhiteList.urls.contains(exchange.getRequest().getPath())){
            return chain.filter(exchange);
        }*/

        //如果token不存在则放行
        if(token==null){
            return chain.filter(exchange);
        }

        try {
            //校验token合法性，并解析
            String userName = JWTUtil.parseToken(token);
            //把用户名设置进请求头
            headers = HttpHeaders.writableHttpHeaders(headers);
            headers.set("username",userName);
            //放行
            return chain.filter(exchange);
        } catch (Exception e) {
            log.error("token非法或者过期 {}",e.getMessage());
            //如果token解析失败（有可能token非法或者过期），则返回
            ServerHttpResponse response = exchange.getResponse();
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            Res res = Res.fail().setCode(ResCode.TOKEN_EXPIRED).setMessage("token非法或者过期");
            DataBuffer buffer = response.bufferFactory().wrap(JSONUtil.toJsonStr(res).getBytes(StandardCharsets.UTF_8));
            return response.writeWith(Mono.just(buffer));
        }
    }
}

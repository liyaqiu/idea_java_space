package com.gzzn.service.gateway.security2.handler;

import cn.hutool.json.JSONUtil;
import com.gzzn.service.gateway.utils.Res;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @author eric
 * @date 2022/9/21 18:02
 **/
@Component
@Slf4j
public class NoLoginHander implements ServerAuthenticationEntryPoint {

    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException e) {
        log.debug("commence");

        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED);

        HttpHeaders headers = response.getHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Res res = Res.fail().setMessage("请先登录");

        DataBuffer buffer = response.bufferFactory().wrap(JSONUtil.toJsonStr(res).getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer));
    }
}

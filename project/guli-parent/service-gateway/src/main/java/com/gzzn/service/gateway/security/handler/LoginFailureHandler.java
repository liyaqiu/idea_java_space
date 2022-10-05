package com.gzzn.service.gateway.security.handler;

import cn.hutool.json.JSONUtil;
import com.gzzn.service.utils.Res;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @author eric
 * @date 2022/9/27 17:26
 **/
@Component
@Slf4j
public class LoginFailureHandler implements ServerAuthenticationFailureHandler {
    @Override
    public Mono<Void> onAuthenticationFailure(WebFilterExchange webFilterExchange, AuthenticationException exception) {
        log.debug("onAuthenticationFailure {}",exception.getMessage());

        String msg = "位置原因请联系管理员";
        if(exception instanceof LockedException){
            msg = "账户被锁住了";
        }

        if(exception instanceof BadCredentialsException){
            msg = "用户名或者密码错误";
        }

        ServerHttpResponse response = webFilterExchange.getExchange().getResponse();
        HttpHeaders headers = response.getHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //返回
        Res res = Res.fail().setMessage(msg);
        DataBuffer buffer = response.bufferFactory().wrap(JSONUtil.toJsonStr(res).getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer));
    }
}

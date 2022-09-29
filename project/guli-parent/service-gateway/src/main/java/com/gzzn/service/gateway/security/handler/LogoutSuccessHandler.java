package com.gzzn.service.gateway.security.handler;

import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONUtil;
import com.gzzn.service.gateway.utils.JWTUtil;
import com.gzzn.service.gateway.utils.Res;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @author eric
 * @date 2022/9/29 16:50
 **/
@Component
@Slf4j
public class LogoutSuccessHandler implements ServerLogoutSuccessHandler {
    @Override
    public Mono<Void> onLogoutSuccess(WebFilterExchange exchange, Authentication authentication) {
        log.debug("onLogoutSuccess");
        ServerHttpResponse response = exchange.getExchange().getResponse();
        HttpHeaders headers = response.getHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        //返回
        Res res = Res.ok().setMessage("退出成功");
        DataBuffer buffer = response.bufferFactory().wrap(JSONUtil.toJsonStr(res).getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer));
    }
}

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
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.nio.charset.StandardCharsets;

/**
 * @author eric
 * @date 2022/9/27 17:01
 **/
@Component
@Slf4j
public class LoginSuccessHandler implements ServerAuthenticationSuccessHandler {
    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {

        /*UserDetails user = (UserDetails) authentication.getPrincipal();
        String username = user.getUsername();*/
        log.debug("onAuthenticationSuccess {}",authentication.getName());

        ServerHttpResponse response = webFilterExchange.getExchange().getResponse();
        HttpHeaders headers = response.getHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        //根据用户名生成jwtToken
        String token = JWTUtil.createToken(authentication.getName());
        //返回
        Res res = Res.ok().setData(MapUtil.builder().put("token", token).build());
        DataBuffer buffer = response.bufferFactory().wrap(JSONUtil.toJsonStr(res).getBytes(StandardCharsets.UTF_8));

        return response.writeWith(Mono.just(buffer));
    }
}

package com.gzzn.service.gateway.security2.handler;

import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONUtil;
import com.gzzn.service.gateway.utils.JWTUtil;
import com.gzzn.service.gateway.utils.Res;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.servlet.ServletOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

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
        //可以存入redis来做token过期
        //返回
        Res res = Res.ok().setData(MapUtil.builder().put("token", token).build());
        System.out.println(token);
        DataBuffer buffer = response.bufferFactory().wrap(JSONUtil.toJsonStr(res).getBytes(StandardCharsets.UTF_8));
        return response.writeWith(Mono.just(buffer));
    }
}

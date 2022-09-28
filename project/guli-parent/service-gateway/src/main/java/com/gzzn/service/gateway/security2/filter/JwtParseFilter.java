package com.gzzn.service.gateway.security2.filter;

import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONUtil;
import com.gzzn.service.gateway.utils.JWTUtil;
import com.gzzn.service.gateway.utils.Res;
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

    public static void main(String[] args) throws Exception {
        //根据用户名生成jwtToken
        String token = JWTUtil.createToken("liyaqiu");
        //可以存入redis来做token过期
        //返回
        Res res = Res.ok().setData(MapUtil.builder().put("token", token).build());

        String userName = JWTUtil.parseToken(token);
        System.out.println(userName);
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        log.debug("filter");
        HttpHeaders headers = exchange.getRequest().getHeaders();
        String token = headers.getFirst("token");


        //如果token不存在则放行
        if(token==null){
            return chain.filter(exchange);
        }

        try {
            //校验tokne是否合法
            //TODO 理论上这里做了解析后面就不需要解析，但是由于不能修改请求头的原因，暂时这样做，这样做会存在一个时间差问题，导致后面json二次解析可能出现失败
            JWTUtil.check(token);
            return chain.filter(exchange);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("token非法或者过期 {}",e.getMessage());
            //如果token解析失败（有可能token非法或者过期），则返回
            ServerHttpResponse response = exchange.getResponse();
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            Res res = Res.fail().setMessage("token非法或者过期");
            DataBuffer buffer = response.bufferFactory().wrap(JSONUtil.toJsonStr(res).getBytes(StandardCharsets.UTF_8));
            return response.writeWith(Mono.just(buffer));
        }
    }
}

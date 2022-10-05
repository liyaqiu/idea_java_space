package com.gzzn.service.gateway.securitybak.filter;

import cn.hutool.json.JSONUtil;
import com.gzzn.service.utils.Res;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

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

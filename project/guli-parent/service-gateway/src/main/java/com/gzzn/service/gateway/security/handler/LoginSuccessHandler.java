package com.gzzn.service.gateway.security.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * @author eric
 * @date 2022/9/21 15:13
 **/
//@Component
@Slf4j
public class LoginSuccessHandler implements ServerAuthenticationSuccessHandler {
    /*@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.debug("onAuthenticationSuccess");
        response.setContentType("application/json;charset=UTF-8");
        try(ServletOutputStream outputStream = response.getOutputStream()) {

            //生成jwtToken
            String token = JWTUtil.createToken(authentication.getName());
            //返回
            Res res = Res.ok().setData(MapUtil.builder().put("token", token));
            outputStream.write(JSONUtil.toJsonStr(res).getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
        }
    }*/

    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        log.debug("onAuthenticationSuccess");
        return Mono.empty();
    }
}

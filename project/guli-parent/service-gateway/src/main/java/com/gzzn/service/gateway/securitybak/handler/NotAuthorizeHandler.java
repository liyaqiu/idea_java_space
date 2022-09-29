package com.gzzn.service.gateway.securitybak.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @author eric
 * @date 2022/9/21 18:03
 **/
//@Component
@Slf4j
public class NotAuthorizeHandler implements ServerAccessDeniedHandler {
    /*@Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.debug("commence");
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        try(ServletOutputStream outputStream = response.getOutputStream()) {
            Res res = Res.fail().setMessage(accessDeniedException.getMessage());
            outputStream.write(JSONUtil.toJsonStr(res).getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
        }
    }*/

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, AccessDeniedException denied) {
        log.info("handle");
        return Mono.empty();
    }
}

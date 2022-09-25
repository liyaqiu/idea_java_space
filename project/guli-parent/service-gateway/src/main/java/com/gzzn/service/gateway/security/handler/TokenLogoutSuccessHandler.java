package com.gzzn.service.gateway.security.handler;

import cn.hutool.json.JSONUtil;

import com.gzzn.service.gateway.utils.Res;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author eric
 * @date 2022/9/22 12:35
 **/
@Slf4j
//@Component
public class TokenLogoutSuccessHandler implements ServerLogoutSuccessHandler {
    /*@Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.debug("onLogoutSuccess");

        if(authentication!=null){
            new SecurityContextLogoutHandler().logout(request,response,authentication);
        }

        response.setContentType("application/json;charset=UTF-8");
        try(ServletOutputStream outputStream = response.getOutputStream()) {
            //返回
            Res res = Res.ok();
            outputStream.write(JSONUtil.toJsonStr(res).getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
        }
    }*/

    @Override
    public Mono<Void> onLogoutSuccess(WebFilterExchange exchange, Authentication authentication) {
        log.info("onLogoutSuccess");
        return Mono.empty();
    }
}

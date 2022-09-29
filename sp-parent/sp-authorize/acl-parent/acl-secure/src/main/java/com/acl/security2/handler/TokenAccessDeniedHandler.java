package com.acl.security2.handler;

import cn.hutool.json.JSONUtil;
import com.gzzn.service.common.utils.Res;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author eric
 * @date 2022/9/21 18:03
 **/
@Component
@Slf4j
public class TokenAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        log.debug("commence");
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        try(ServletOutputStream outputStream = response.getOutputStream()) {
            Res res = Res.fail().setMessage(accessDeniedException.getMessage());
            outputStream.write(JSONUtil.toJsonStr(res).getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
        }
    }
}

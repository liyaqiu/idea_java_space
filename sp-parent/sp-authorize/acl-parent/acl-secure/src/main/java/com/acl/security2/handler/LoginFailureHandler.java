package com.acl.security2.handler;

import cn.hutool.json.JSONUtil;
import com.acl.security2.utils.Result;
import com.gzzn.service.common.utils.Res;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author eric
 * @date 2022/9/21 15:13
 **/
@Component
@Slf4j
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        log.debug("onAuthenticationFailure");
        response.setContentType("application/json;charset=UTF-8");
        try(ServletOutputStream outputStream = response.getOutputStream()) {
            Result res = Result.fail(exception.getMessage());
            outputStream.write(JSONUtil.toJsonStr(res).getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
        }
    }
}

package com.acl.secure.handler;

import com.acl.common.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author eric
 * @date 2022/3/24 10:17
 * 未认证处理
 **/
@Slf4j
public class MyAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.info("未登录");
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(ResultUtil.errJson(ResultUtil.State.UN_AUTHTICATION));
    }
}

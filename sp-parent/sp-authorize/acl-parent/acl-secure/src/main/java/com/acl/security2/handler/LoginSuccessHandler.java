package com.acl.security2.handler;

import cn.hutool.core.map.MapUtil;
import cn.hutool.json.JSONUtil;
import com.gzzn.service.common.utils.JWTUtil;
import com.gzzn.service.common.utils.Res;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
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
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
    @Override
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
    }

}

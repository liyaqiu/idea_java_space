package com.gzzn.service.gateway.securitybak.config;


import com.gzzn.service.gateway.securitybak.filter.LoginFilter;
import com.gzzn.service.gateway.securitybak.handler.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * @author eric
 * @date 2022/3/19 15:02
 **/
@Slf4j
//@EnableWebFluxSecurity
public class SecurityConfig2 {
    private final static String[] URL_WHITELIST = {"/login","/logout","/dev-api/captcha","/favicon.ico"};
    @Autowired
    private NotLoginHander notLoginHander;
    @Autowired
    private LoginFilter loginFilter;
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http.cors()
                //禁用csrf校验
                .and()
                .csrf().disable()
                    .authorizeExchange()
                    //设置白名单
                    .pathMatchers(URL_WHITELIST).permitAll()
                    //其他的任何url都需要进行认证
                    .anyExchange().authenticated()
                //异常处理
                .and()
                    .exceptionHandling()
                    //未登陆的处理
                    .authenticationEntryPoint(notLoginHander)
                .and()
                    //验证码过滤器
                    //.addFilterBefore(captchaFilter, SecurityWebFiltersOrder.FORM_LOGIN)
                    //登陆过滤器
                    .addFilterAt(loginFilter, SecurityWebFiltersOrder.FORM_LOGIN);
        return http.build();
    }







}

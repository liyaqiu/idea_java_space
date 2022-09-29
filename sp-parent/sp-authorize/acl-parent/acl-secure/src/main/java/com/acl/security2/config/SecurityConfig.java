package com.acl.security2.config;

import com.acl.security2.TokenAuthenticationEntryPoint;
import com.acl.security2.filter.CaptchaFilter;
import com.acl.security2.filter.TokenAuthenticationFilter;
import com.acl.security2.handler.LoginFailureHandler;
import com.acl.security2.handler.LoginSuccessHandler;

import com.acl.security2.handler.TokenAccessDeniedHandler;
import com.acl.security2.handler.TokenLogoutSuccessHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * @author eric
 * @date 2022/3/19 15:02
 **/
@Slf4j
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true) //允许方法注解 @PreAuthorize("hasAuthority('p2')")权限
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //url白名单
    private final static String[] URL_WHITELIST = {"/login","/logout","/captcha","/favicon.ico"};
    @Autowired
    private LoginSuccessHandler loginSuccessHandler;
    @Autowired
    private LoginFailureHandler loginFailureHandler;
    @Autowired
    private TokenLogoutSuccessHandler tokenLogoutSuccessHandler;
    @Autowired
    private CaptchaFilter captchaFilter;
    @Autowired
    private TokenAuthenticationEntryPoint tokenAuthenticationEntryPoint;
    @Autowired
    private TokenAccessDeniedHandler tokenAccessDeniedHandler;
    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public TokenAuthenticationFilter getTokenAuthenticationFilter() throws Exception {
        return new TokenAuthenticationFilter(authenticationManager());
    }



    /*
    * 核心配置
    * */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("核心配置");
        //允许cors
        http.cors()
        //禁用csrf校验
        .and()
                .csrf().disable()
        //登录配置
        .formLogin()
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailureHandler)
        .and()
                .sessionManagement()
                //禁用session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        //退出登录配置
        .and()
                .logout()
                .logoutSuccessHandler(tokenLogoutSuccessHandler)
        //配置拦截规则
        .and()
                .authorizeRequests()
                //设置白名单
                .antMatchers(URL_WHITELIST).permitAll()
                //其他的任何url都需要进行认证
                .anyRequest().authenticated()
        //配置异常处理器
        .and()
                .exceptionHandling()
                //未登陆的处理
                .authenticationEntryPoint(tokenAuthenticationEntryPoint)
                //没权限的处理
                .accessDeniedHandler(tokenAccessDeniedHandler)
        //配置自定义过滤器
        .and()
                //配置token认证过滤器
                .addFilter(getTokenAuthenticationFilter())
                //验证码过滤器在账号密码认证过滤器之前
                .addFilterBefore(captchaFilter, UsernamePasswordAuthenticationFilter.class);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }
}

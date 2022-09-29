package com.gzzn.service.gateway.securitybak.config;


import com.gzzn.service.gateway.securitybak.filter.CaptchaFilter;
import com.gzzn.service.gateway.securitybak.filter.LoginFilter;
import com.gzzn.service.gateway.securitybak.handler.*;
import com.gzzn.service.gateway.securitybak.manager.UsernamePasswordAuthenticationManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * @author eric
 * @date 2022/3/19 15:02
 **/
@Slf4j
//@EnableWebFluxSecurity
public class SecurityConfig{

    //@Autowired
    //private ReactiveAuthenticationManager authenticationManager;
    //url白名单
    private final static String[] URL_WHITELIST = {"/login","/logout","/dev-api/captcha","/favicon.ico"};
    @Autowired
    private LoginSuccessHandler loginSuccessHandler;
    @Autowired
    private LoginFailureHandler loginFailureHandler;
    @Autowired
    private TokenLogoutSuccessHandler tokenLogoutSuccessHandler;
    @Autowired
    private CaptchaFilter captchaFilter;
    @Autowired
    private NotLoginHander notLoginHander;
    @Autowired
    private NotAuthorizeHandler notAuthorizeHandler;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private LoginFilter loginFilter;

    @Autowired
    UsernamePasswordAuthenticationManager authenticationManager;

    /*@Bean
    public TokenAuthenticationFilter tokenAuthenticationFilter(){
        return  new TokenAuthenticationFilter(authenticationManager);
    }*/


    //密码编码器
    /*@Bean
    public PasswordEncoder passwordEncoder(){
        log.info("passwordEncoder");
        return NoOpPasswordEncoder.getInstance();
        //return new BCryptPasswordEncoder();
    }*/



    /*@Bean
    public UserDetailsService userDetailsService(){
        return new UserDetailsService(){

            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                log.debug("UserDetailsService");
                return null;
            }
        };
    }*/

    /*
    * 核心配置
    * */
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity  http) throws Exception {
        log.info("核心配置 {}");

        http.cors()
                //禁用csrf校验
                .and()
                    .csrf().disable()
                //登录配置
                /*.formLogin()
                    .authenticationSuccessHandler(loginSuccessHandler)
                    .authenticationFailureHandler(loginFailureHandler)*/

               // .and()
                //退出登录配置
                    .logout()
                    .logoutSuccessHandler(tokenLogoutSuccessHandler)
                //配置拦截规则
                .and().authenticationManager(authenticationManager)
                    .authorizeExchange()
                    //设置白名单
                    .pathMatchers(URL_WHITELIST).permitAll()
                    //其他的任何url都需要进行认证
                    .anyExchange().authenticated()
                //异常处理
                .and()
                    .exceptionHandling()
                    //未登陆的处理
                    //.authenticationEntryPoint(notLoginHander)
                    //没权限的处理
                    .accessDeniedHandler(notAuthorizeHandler)
                    //配置自定义过滤器
                .and()

                    //验证码过滤器
                    //.addFilterBefore(captchaFilter, SecurityWebFiltersOrder.FORM_LOGIN)
                    //登陆过滤器
                    .addFilterAt(loginFilter, SecurityWebFiltersOrder.FORM_LOGIN);
                    //配置token认证过滤器
                    //.addFilterAt(tokenAuthenticationFilter(), SecurityWebFiltersOrder.AUTHENTICATION);
        return http.build();
    }


   /* @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }*/
}

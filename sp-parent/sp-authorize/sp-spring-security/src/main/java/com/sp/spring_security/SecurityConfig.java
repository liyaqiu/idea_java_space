package com.sp.spring_security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author eric
 * @date 2022/3/19 15:02
 **/
@Slf4j
@Configuration
/*
    增加启用方法权限配置
        @EnableGlobalMethodSecurity(securedEnabled =true,prePostEnabled = true)
    可以在方法上面增加如下注解
        @PreAuthorize("hasAuthority('p2')")权限
        @Secured({"ROLE_admin"})角色
*/
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    //认证数据与
    @Bean
    public UserDetailsService infoDatabase(){
        log.info("infoDatabase");
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                log.info("loadUserByUsername {}",username);

                if (username == null) {
                    throw new UsernameNotFoundException(username);
                }
                //数据库接入
                return User.withUsername("liyaqiu").password("123456").authorities("resource2Permission","p1").disabled(false).accountLocked(false).build();
            }
        };


//        UserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("liyaqiu").password("123456").authorities("resource1Permission").build());
//        manager.createUser(User.withUsername("eric").password("123456").authorities("resource2Permission").build());
//        return manager;
    }

    /**
     * 方式2
     * */
    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        log.info("AuthenticationManagerBuilder auth configure");
        //auth.inMemoryAuthentication().withUser("eric").password("123456").authorities("resource2Permission");
        //auth.userDetailsService(infoDatabase()).passwordEncoder(passwordEncoder());
    }*/

    //密码编码器
    @Bean
    public PasswordEncoder passwordEncoder(){
        log.info("passwordEncoder");
        return NoOpPasswordEncoder.getInstance();
        //return new BCryptPasswordEncoder();
    }


    //拦截机制
    /*
    *AccessDecisionManager  投票策略接口
    * */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("拦截机制");
        //http.exceptionHandling().accessDeniedPage("403.html"); //自定义错误页面

        http.exceptionHandling()
                    .authenticationEntryPoint(new AuthenticationEntryPoint() {
                        @Override
                        public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                            response.getWriter().print("no login");
                        }
                    })
                .and()
                    .authorizeRequests()
                    .antMatchers(HttpMethod.DELETE,"/a/b/c").hasRole("admin")
                    .antMatchers("/security/resource1").hasAuthority("resource2Permission")//授权认证
                    .antMatchers("/security/resource1").hasAuthority("resource1Permission")//授权认证
                    .antMatchers("/security/resource2").hasAuthority("resource2Permission")//授权认证
                    .antMatchers("/security/**").authenticated()//认证
                    .anyRequest().permitAll() //permitAll 放行全部资源
                .and() //登录管理
                    .formLogin() //设置表单登录  post请求
                    //.loginPage("https://www.baidu.com")
                    //设置登录页面地址
                    //.loginPage("/login-dir/login.html")
                    .loginPage("/loginpage")//AuthenticationEntryPoint
                    //设置提交用户名参数
                    .usernameParameter("name")
                    //设置提交密码参数
                    .passwordParameter("pass")
                    //设置表单提交的地址
                    .loginProcessingUrl("/submitlogin")
                    //设置成功够转发的地址
                    .successForwardUrl("/success")
                    //permitAll 放行全部资源
                    .permitAll()
                .and()//退出
                    .logout()
                    .logoutUrl("/mylogout") //普通登陆get请求，  csrf是post请求
                    .logoutSuccessUrl("/out")
                    .permitAll()
                .and()//会话管理
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)//单体应用利用session来保存，如果没有session就创建
                    //.invalidSessionUrl("/login-dir/login.html") //session过期调转的url
                .and()
                    .rememberMe() //开启记住我功能 remember-me
                    .rememberMeParameter("remember-me")
                    .tokenValiditySeconds(20)
                    .userDetailsService(infoDatabase())
                    .tokenRepository(persistentTokenRepository())
                .and()
                .csrf().csrfTokenRepository(csrfTokenRepository())
                .disable() //禁用
                ;

    }
    /**
     * csrf存储位置
     * */
    @Bean
    public CsrfTokenRepository csrfTokenRepository(){
        log.info("CsrfTokenRepository csrf存储位置");
        return new HttpSessionCsrfTokenRepository();
    }



    /*
    记住我功能实现
    * */
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        log.info("PersistentTokenRepository 记住我功能开启");
        return new MyRememberMeTokenStore();
    }
}

package com.sp.spring_security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
@Configuration(proxyBeanMethods = true)
//@EnableGlobalMethodSecurity(securedEnabled =true,prePostEnabled = true) //????????????????????????
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    //???????????????
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
                //???????????????
                return User.withUsername("liyaqiu").password("123456").authorities("resource2Permission","p1").disabled(false).accountLocked(false).build();
            }
        };


//        UserDetailsManager manager = new InMemoryUserDetailsManager();
//        manager.createUser(User.withUsername("liyaqiu").password("123456").authorities("resource1Permission").build());
//        manager.createUser(User.withUsername("eric").password("123456").authorities("resource2Permission").build());
//        return manager;
    }

    /**
     * ??????2
     * */
    /*@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        log.info("AuthenticationManagerBuilder auth configure");
        //auth.inMemoryAuthentication().withUser("eric").password("123456").authorities("resource2Permission");
        //auth.userDetailsService(infoDatabase()).passwordEncoder(passwordEncoder());
    }*/

    //???????????????
    @Bean
    public PasswordEncoder passwordEncoder(){
        log.info("passwordEncoder");
        return NoOpPasswordEncoder.getInstance();
        //return new BCryptPasswordEncoder();
    }


    //????????????
    /*
    *AccessDecisionManager  ??????????????????
    * */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("????????????");
        //http.exceptionHandling().accessDeniedPage("403.html"); //?????????????????????

        http.exceptionHandling()
                    .authenticationEntryPoint(new AuthenticationEntryPoint() {
                        @Override
                        public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
                            response.getWriter().print("no login");
                        }
                    })
                .and()
                    .authorizeRequests()
                    .antMatchers("/security/resource1").hasAuthority("resource2Permission")//????????????
                    .antMatchers("/security/resource1").hasAuthority("resource1Permission")//????????????
                    .antMatchers("/security/resource2").hasAuthority("resource2Permission")//????????????
                    .antMatchers("/security/**").authenticated()//??????
                    .anyRequest().permitAll() //permitAll ??????????????????
                .and() //????????????
                    .formLogin() //??????????????????  post??????
                    //.loginPage("https://www.baidu.com")
                    //????????????????????????
                    //.loginPage("/login-dir/login.html")
                    .loginPage("/loginpage")//AuthenticationEntryPoint
                    //???????????????????????????
                    .usernameParameter("name")
                    //????????????????????????
                    .passwordParameter("pass")
                    //???????????????????????????
                    .loginProcessingUrl("/submitlogin")
                    //??????????????????????????????
                    .successForwardUrl("/success")
                    //permitAll ??????????????????
                    .permitAll()
                .and()//??????
                    .logout()
                    .logoutUrl("/mylogout") //????????????get?????????  csrf???post??????
                    .logoutSuccessUrl("/out")
                    .permitAll()
                .and()//????????????
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)//??????????????????session????????????????????????session?????????
                    //.invalidSessionUrl("/login-dir/login.html") //session???????????????url
                .and()
                    .rememberMe() //????????????????????? remember-me
                    .rememberMeParameter("remember-me")
                    .tokenValiditySeconds(20)
                    .userDetailsService(infoDatabase())
                    .tokenRepository(persistentTokenRepository())
                .and()
                .csrf().csrfTokenRepository(csrfTokenRepository())
                .disable() //??????
                ;

    }
    /**
     * csrf????????????
     * */
    @Bean
    public CsrfTokenRepository csrfTokenRepository(){
        log.info("CsrfTokenRepository csrf????????????");
        return new HttpSessionCsrfTokenRepository();
    }



    /*
    ?????????????????????
    * */
    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        log.info("PersistentTokenRepository ?????????????????????");
        return new MyRememberMeTokenStore();
    }
}

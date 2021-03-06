package com.acl.secure.config;

import com.acl.secure.filter.MyBasicAuthenticationFilter;
import com.acl.secure.filter.MyUsernamePasswordAuthenticationFilter;
import com.acl.secure.handler.MyAuthenticationEntryPoint;
import com.acl.secure.handler.MyLogoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author eric
 * @date 2022/3/19 15:02
 **/
@Slf4j
@Configuration(proxyBeanMethods = false)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    StringRedisTemplate redisTemplate;


    //???????????????
    @Bean
    public PasswordEncoder passwordEncoder(){
        log.info("passwordEncoder");
        return NoOpPasswordEncoder.getInstance();
        //return new BCryptPasswordEncoder();
    }

    //??????????????????
    @Bean
    public UserDetailsService userDetailsService(){
        log.info("userDetailsService");
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                log.info("userDetailsService {}",username);

                if ("liyaqiu".equals(username)) {
                    //???????????????
                    //return User.withUsername("liyaqiu").password("123456").authorities("resource2Permission","p1")/*.disabled(false).accountLocked(false)*/.build();
                    return User.withUsername("liyaqiu").password("123456").authorities("p1").build();
                }else{
                    throw new UsernameNotFoundException(username);
                }
            }
        };
    }


    //????????????
    /*
    *AccessDecisionManager  ??????????????????
    * */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("security ????????????");
        http
                //??????csrf
            .csrf()
                .disable()
            .authorizeRequests()
                .antMatchers("/index","/favicon.ico").permitAll()
                .antMatchers("/security/resource1").hasAuthority("p1")//????????????
                .antMatchers("/security/resource2").hasAuthority("p2")//????????????
                //?????????????????????????????????
                .anyRequest().authenticated()
            .and()
                .exceptionHandling()
                //???????????????
                .authenticationEntryPoint(new MyAuthenticationEntryPoint())
            .and()
                .logout()
                .logoutUrl("/admin/acl/logout")
                .addLogoutHandler(new MyLogoutHandler(redisTemplate))
            .and()
                //????????????
                .addFilter(new MyUsernamePasswordAuthenticationFilter(redisTemplate,authenticationManager()))
                //????????????
                .addFilter(new MyBasicAuthenticationFilter(redisTemplate,authenticationManager()))
            ;

    }
}

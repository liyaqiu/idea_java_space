package com.acl.security1.config;

import com.acl.security1.filter.MyBasicAuthenticationFilter;
import com.acl.security1.filter.MyUsernamePasswordAuthenticationFilter;
import com.acl.security1.handler.MyAuthenticationEntryPoint;
import com.acl.security1.handler.MyLogoutHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author eric
 * @date 2022/3/19 15:02
 **/
@Slf4j
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    StringRedisTemplate redisTemplate;


    //密码编码器
    @Bean
    public PasswordEncoder passwordEncoder(){
        log.info("passwordEncoder");
        return NoOpPasswordEncoder.getInstance();
        //return new BCryptPasswordEncoder();
    }

    //用户信息服务
    @Bean
    public UserDetailsService userDetailsService(){
        log.info("userDetailsService");
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                log.info("userDetailsService {}",username);

                if ("liyaqiu".equals(username)) {
                    //数据库接入
                    //return User.withUsername("liyaqiu").password("123456").authorities("resource2Permission","p1")/*.disabled(false).accountLocked(false)*/.build();
                    return User.withUsername("liyaqiu").password("123456").authorities("p1").build();
                }else{
                    throw new UsernameNotFoundException(username);
                }
            }
        };
    }


    //拦截机制
    /*
    *AccessDecisionManager  投票策略接口
    * */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        log.info("security 核心配置");
        http
                //禁用csrf
            .csrf()
                .disable()
            .authorizeRequests()
                .antMatchers("/index","/favicon.ico").permitAll()
                .antMatchers("/security/resource1").hasAuthority("p1")//授权认证
                .antMatchers("/security/resource2").hasAuthority("p2")//授权认证
                //任何请求需要认证和授权
                .anyRequest().authenticated()
            .and()
                .exceptionHandling()
                //未认证处理
                .authenticationEntryPoint(new MyAuthenticationEntryPoint())
            .and()
                .logout()
                .logoutUrl("/admin/acl/logout")
                .addLogoutHandler(new MyLogoutHandler(redisTemplate))
            .and()
                //登陆认证
                .addFilter(new MyUsernamePasswordAuthenticationFilter(redisTemplate,authenticationManager()))
                //授权认证
                .addFilter(new MyBasicAuthenticationFilter(redisTemplate,authenticationManager()))
            ;

    }
}

package com.gzzn.service.gateway.security2.config;



import com.gzzn.service.gateway.security2.handler.LoginFailureHandler;
import com.gzzn.service.gateway.security2.handler.LoginSuccessHandler;
import com.gzzn.service.gateway.security2.handler.NoAuthorityHandler;
import com.gzzn.service.gateway.security2.handler.NoLoginHander;
import com.gzzn.service.gateway.security2.repository.MyContextRepository;
import com.gzzn.service.gateway.security2.service.UserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * https://blog.csdn.net/qq_43692950/article/details/122513687
 * @author eric
 * @date 2022/3/19 15:02
 **/
@Slf4j
//@EnableReactiveMethodSecurity
@EnableWebFluxSecurity
public class SecurityConfig {


    /*@Bean
    public ReactiveUserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("admin").password(passwordEncoder().encode("1234")).authorities("admin").build();
        return new MapReactiveUserDetailsService(user);
    }*/

    @Autowired
    LoginSuccessHandler loginSuccessHandler;
    @Autowired
    LoginFailureHandler loginFailureHandler;
    @Autowired
    NoLoginHander noLoginHander;
    @Autowired
    NoAuthorityHandler noAuthorityHandler;
    @Autowired
    MyContextRepository myContextRepository;

    @Autowired
    UserDetailService userDetailService;

    @Bean
    public ReactiveAuthenticationManager authenticationManager() {
        UserDetailsRepositoryReactiveAuthenticationManager authenticationManager = new UserDetailsRepositoryReactiveAuthenticationManager(userDetailService);
        authenticationManager.setPasswordEncoder(passwordEncoder());
        return authenticationManager;
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityWebFilterChain webFluxSecurityFilterChain(ServerHttpSecurity http) throws Exception {
        http
                .authorizeExchange()
                    .pathMatchers(HttpMethod.GET,"/dev-api/edu/subject/all").hasAuthority("subject:list")
                    .pathMatchers(HttpMethod.GET,"/dev-api/edu/teacher/all").hasAuthority("teacher:list")
                    .pathMatchers(HttpMethod.GET,"/dev-api/edu/teacher/*").hasAuthority("teacher:get")
                    .pathMatchers(HttpMethod.DELETE,"/dev-api/edu/teacher/*").hasAuthority("teacher:delete")
                    .pathMatchers("/mylogin","/mylogout").permitAll()
                    .anyExchange().authenticated()
                .and()
                    //.securityContextRepository(myContextRepository)
                    .formLogin()
                    .loginPage("/mylogin")
                    .authenticationSuccessHandler(loginSuccessHandler) //登陆成功
                    .authenticationFailureHandler(loginFailureHandler) //登陆失败
                .and()
                    .exceptionHandling()
                    .authenticationEntryPoint(noLoginHander) //没登录，配置这个就不会返回默认的登陆界面了
                    .accessDeniedHandler(noAuthorityHandler) //没权限
                .and().cors().disable().csrf().disable();
        return http.build();
    }


    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
    }
}

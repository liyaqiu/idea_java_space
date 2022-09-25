package com.gzzn.service.gateway.security2.config;


import com.gzzn.service.gateway.security.filter.LoginFilter;
import com.gzzn.service.gateway.security.handler.NotLoginHander;
import com.gzzn.service.gateway.security2.service.UserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.userdetails.MapReactiveUserDetailsService;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
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

    /*@Bean
    SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) throws Exception {
        http.authorizeExchange()
                .pathMatchers("/admin/**").hasRole("admin")
                .pathMatchers("/common/**").hasRole("coommon")
                .and().formLogin()
                .and().cors().disable();
        return http.build();
    }*/

}

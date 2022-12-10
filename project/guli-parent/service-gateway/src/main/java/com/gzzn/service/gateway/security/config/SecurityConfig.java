package com.gzzn.service.gateway.security.config;



import cn.hutool.core.util.ArrayUtil;
import com.gzzn.service.gateway.security.encoder.MD5PasswordEncoder;
import com.gzzn.service.gateway.security.filter.JwtParseFilter;
import com.gzzn.service.gateway.security.handler.*;
import com.gzzn.service.gateway.security.repository.SecureContextRepository;
import com.gzzn.service.gateway.security.service.UserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsConfigurationSource;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import reactor.core.publisher.Mono;

import java.util.Arrays;

/**
 * https://blog.csdn.net/qq_43692950/article/details/122513687
 * https://blog.csdn.net/qq_43692950/article/details/122513687?ops_request_misc=%257B%2522request%255Fid%2522%253A%2522166426668116800184172884%2522%252C%2522scm%2522%253A%252220140713.130102334.pc%255Fblog.%2522%257D&request_id=166426668116800184172884&biz_id=0&utm_medium=distribute.pc_search_result.none-task-blog-2~blog~first_rank_ecpm_v1~rank_v31_ecpm-6-122513687-null-null.nonecase&utm_term=SpringSecurity&spm=1018.2226.3001.4450
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
    final String BASE_URL = "/dev-api";

    @Autowired
    LoginSuccessHandler loginSuccessHandler;
    @Autowired
    LoginFailureHandler loginFailureHandler;
    @Autowired
    NoLoginHander noLoginHander;
    @Autowired
    NoAuthorityHandler noAuthorityHandler;
    @Autowired
    SecureContextRepository secureContextRepository;
    @Autowired
    JwtParseFilter jwtParseFilter;
    @Autowired
    LogoutHandler logoutHandler;
    @Autowired
    LogoutSuccessHandler logoutSuccessHandler;

    @Autowired
    UserDetailService userDetailService;

    @Bean
    public ReactiveAuthenticationManager authenticationManager() {
        UserDetailsRepositoryReactiveAuthenticationManager authenticationManager = new UserDetailsRepositoryReactiveAuthenticationManager(userDetailService);
        authenticationManager.setPasswordEncoder(passwordEncoder());
        return authenticationManager;
    }

    /*start*/
    /*@Autowired
    ReactiveAuthenticationManager reactiveAuthenticationManager;
    public void test(){
        log.info("{}","controller 和 service实现思路");
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken("username", "password");
        ReactiveSecurityContextHolder.withAuthentication(authenticationToken);
        Mono<Authentication> authenticate = reactiveAuthenticationManager.authenticate(authenticationToken);
        Object principal = authenticate.block().getPrincipal();
    }*/

    /*{
        //自定义ajax登陆，去掉配置.formLogin()即可，然后根据如下思路进行实现
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        AuthenticationContextHolder.setContext(authenticationToken);
        // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername
        authentication = authenticationManager.authenticate(authenticationToken);
    }*/
    /*end*/

    @Bean
    PasswordEncoder passwordEncoder() {
        return new MD5PasswordEncoder();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
        corsConfiguration.setAllowedMethods(Arrays.asList("*"));
        corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
        corsConfiguration.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",corsConfiguration);
        return source;
    }

    public static void main(String[] args) {
        System.out.println(new BCryptPasswordEncoder().encode("123456"));
        System.out.println(new BCryptPasswordEncoder().matches("123456", "$2a$10$2lOoC6KMp8RMHS85Ryu4I.wkw2mUYGi394eu0u48ZaqX.MKSIKsRq"));
    }

    @Bean
    SecurityWebFilterChain webFluxSecurityFilterChain(ServerHttpSecurity http) throws Exception {
        String[] whitelist = ArrayUtil.toArray(WhiteList.urls,String.class);
        http
            .authorizeExchange()
                //添加讲师
                .pathMatchers(HttpMethod.POST,BASE_URL+"/edu/teacher").hasAuthority("teacher.add")
                //删除讲师
                .pathMatchers(HttpMethod.DELETE,BASE_URL+"/edu/teacher/*").hasAuthority("teacher.delete")
                //修改讲师
                .pathMatchers(HttpMethod.PUT,BASE_URL+"/edu/teacher").hasAuthority("teacher.update")
                //条件分页查询讲师
                .pathMatchers(HttpMethod.GET,BASE_URL+"/edu/teacher/*/*").hasAuthority("teacher.list")

                //添加课程
                .pathMatchers(HttpMethod.POST,BASE_URL+"/edu/course").hasAuthority("course.add")
                //删除课程
                .pathMatchers(HttpMethod.DELETE,BASE_URL+"/edu/course/*").hasAuthority("course.delete")
                //修改课程
                .pathMatchers(HttpMethod.PUT,BASE_URL+"/edu/course").hasAuthority("course.update")
                //条件分页查询课程
                .pathMatchers(HttpMethod.GET,BASE_URL+"/edu/course/*/*").hasAuthority("course.list")

                //.pathMatchers(whitelist).permitAll() //白名单，不需要认证和鉴权
                .anyExchange().authenticated()
            .and()
                //提供了仓库，覆盖默认的WebSessionServerSecurityContextRepository，默认仓库用的是session来做处理
                //以及响应头的cookie里面也不会存在session信息了
                .securityContextRepository(secureContextRepository)
                .formLogin()
                .loginPage("/dev-api/mylogin")//post请求 默认不会拦截此接口
                .authenticationSuccessHandler(loginSuccessHandler) //登陆成功
                .authenticationFailureHandler(loginFailureHandler) //登陆失败
            .and()//退出
                .logout()
                .logoutUrl("/dev-api/mylogout") //post请求 默认不会拦截此接口
                .logoutHandler(logoutHandler)
                .logoutSuccessHandler(logoutSuccessHandler)
            .and()
                .exceptionHandling()
                .authenticationEntryPoint(noLoginHander) //没登录，配置这个就不会返回默认的登陆界面了
                .accessDeniedHandler(noAuthorityHandler) //没权限
            .and()
                .addFilterAfter(jwtParseFilter,SecurityWebFiltersOrder.CORS) //添加jwt解析器
                .cors().configurationSource(corsConfigurationSource())
            .and()
                .csrf().disable();
        return http.build();
    }
}

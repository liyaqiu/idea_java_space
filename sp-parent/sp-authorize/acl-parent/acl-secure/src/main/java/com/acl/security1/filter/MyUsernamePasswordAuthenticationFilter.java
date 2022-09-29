package com.acl.security1.filter;

import com.acl.common.utils.JWTTokenUtil;
import com.acl.common.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

/**
 * @author eric
 * @date 2022/3/24 10:33
 * 认证过滤器
 **/
@Slf4j
public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {


    StringRedisTemplate redisTemplate;
    AuthenticationManager authenticationManager;

    public MyUsernamePasswordAuthenticationFilter(StringRedisTemplate redisTemplate,AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;
        this.redisTemplate = redisTemplate;
        super.setPostOnly(false);
        //super.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/admin/acl/login","POST"));
        super.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/admin/acl/login"));
    }

    /**
     * 获取表单提交的数据做初步校验
     * */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        System.out.println("attemptAuthentication");

        //return super.attemptAuthentication(request, response);
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //获取请求体
        try(BufferedReader reader = request.getReader()){
            while (reader.ready()) {
                System.out.println("@@@"+reader.readLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        log.info("获取表单信息并且返回给框架做用户校验 {}  {}",username,password);

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(username, password);
        //传过来的数据和UserDetailsService提供的数据做一个比对校验
        return authenticationManager.authenticate(usernamePasswordAuthenticationToken);
    }


    /**
     * 登录成功
     * */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        //super.successfulAuthentication(request, response, chain, authResult);
        log.info("登录成功，将用户信息以及权限存储到redis");
        UserDetails userDetails = (UserDetails) authResult.getPrincipal();
        String username = userDetails.getUsername();
        //创建token
        String token = JWTTokenUtil.tokenGener(username);
        //把token存储到redis  admin,p2,p3  过期时间可以redis做，也可以jwt做
        redisTemplate.opsForValue().set(username,getPermissions(userDetails));
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(ResultUtil.okJson(ResultUtil.State.OK,token));
    }

    public String getPermissions(UserDetails userDetails){
        StringBuilder sb = new StringBuilder();
        for (GrantedAuthority authority : userDetails.getAuthorities()) {
            sb.append(authority.getAuthority()).append(",");
        }
        return sb.substring(0, sb.length() - 1);
    }

    /**
     * 登录失败
     * */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        super.unsuccessfulAuthentication(request, response, failed);
        log.info("登录失败");
        response.setContentType("text/html;charset=utf-8");
        response.getWriter().write(ResultUtil.errJson(ResultUtil.State.LOGIN_FAIL));
    }
}


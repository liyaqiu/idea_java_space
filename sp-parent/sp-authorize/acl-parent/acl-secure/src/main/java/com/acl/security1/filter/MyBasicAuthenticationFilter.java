package com.acl.security1.filter;

import com.acl.common.utils.JWTTokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author eric
 * @date 2022/3/24 12:27
 * 授权过滤器
 **/
@Slf4j
public class MyBasicAuthenticationFilter extends BasicAuthenticationFilter {


    StringRedisTemplate redisTemplate;
    AuthenticationManager authenticationManager;

    public MyBasicAuthenticationFilter(StringRedisTemplate redisTemplate,AuthenticationManager authenticationManager) {
        super(authenticationManager);
        this.authenticationManager = authenticationManager;
        this.redisTemplate = redisTemplate;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("从redis获取用户权限返回给框架做认证");
        //super.doFilterInternal(request, response, chain);
        //获取用户权限信息
        UsernamePasswordAuthenticationToken authRequest = getAuthentication(request);
        //把用户信息以及权限信息交给框架
        if(authRequest!=null){
            SecurityContextHolder.getContext().setAuthentication(authRequest);
        }else{
            //如果返回Null，token过期，需要重新登陆
            SecurityContextHolder.clearContext();
        }

        chain.doFilter(request, response);
    }





    public UsernamePasswordAuthenticationToken getAuthentication(HttpServletRequest request){
        String token = request.getHeader("token");
        if(token!=null){
            String username = JWTTokenUtil.tokenParse(token);
            if(username == null){
                return null;
            }
            String permessionStr = redisTemplate.opsForValue().get(username);
            if(permessionStr ==null){
                return null;
            }
            List<String> permissions = Arrays.asList(permessionStr);
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            for (String permission : permissions) {
                authorities.add(new SimpleGrantedAuthority(permission));
            }
            return new UsernamePasswordAuthenticationToken(username, token,authorities);
        }
        return null;
    }

}

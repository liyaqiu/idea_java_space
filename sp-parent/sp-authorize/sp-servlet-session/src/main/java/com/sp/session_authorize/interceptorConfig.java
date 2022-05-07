package com.sp.session_authorize;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lyq
 * @date 2022/3/1 3:05
 */
@Configuration
public class interceptorConfig implements WebMvcConfigurer{

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
            registry.addInterceptor(new LoginInterceptor())
                    .addPathPatterns("/session/**");//拦截哪些资源
            registry.addInterceptor(new AuthorizeInterceptor())
                    .addPathPatterns("/session/**");//拦截哪些资源
                    //.excludePathPatterns("/session/login");//排除哪些资源

    }
}

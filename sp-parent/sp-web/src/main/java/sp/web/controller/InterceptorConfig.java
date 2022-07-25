package sp.web.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lyq
 * @date 2022/3/1 3:05
 */
@Configuration
public class InterceptorConfig {

    @Bean
    public WebMvcConfigurer webMvcConfigurer (){
        return  new WebMvcConfigurer () {
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                //registry.addInterceptor(xxx1)
                //registry.addInterceptor(xxx2)
                InterceptorRegistration interceptor = registry.addInterceptor(new LoginInterceptor());
                interceptor.addPathPatterns("/**");//拦截哪些资源
                interceptor.excludePathPatterns("/**/*.html", "/**/*.js","/login","/sessionlogin");//排除哪些资源
            }
        };
    }

}

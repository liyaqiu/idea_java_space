package com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author eric
 * @date 2022/9/23 20:43
 **/
@Configuration
public class CORSConfig implements WebMvcConfigurer{

    //- 更多跨域请求头字段可以参考这里 https://developer.mozilla.org/zh-CN/docs/Web/HTTP/CORS
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") //允许ajax请求的来源网站 response.setHeader("Access-Control-Allow-Origin", "*");
                .allowedMethods("*") //允许ajax请求哪些方法
                //https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Access-Control-Allow-Credentials
                .allowCredentials(true)//是否允许携带Credentials 可以是 cookies、authorization headers 或 TLS client certificates。
                .exposedHeaders("*") //设置ajax能获取到响应头哪些头信息 response.setHeader("Access-Control-Expose-Headers","*");
                .allowedHeaders("*");//允许ajax发送哪些自定义(浏览器默认允许所有除预定义的)请求头信息

    }
}

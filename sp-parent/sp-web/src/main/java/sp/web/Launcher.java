package sp.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author lyq
 * @date 2021/12/4 12:34
 */
@SpringBootApplication
public class Launcher {



    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Launcher.class, args);
    }

}


@Configuration
class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //- 更多跨域请求头字段可以参考这里 https://developer.mozilla.org/zh-CN/docs/Web/HTTP/CORS
        registry.addMapping("/**")
                .allowedOrigins("*") //允许ajax请求的来源网站 response.setHeader("Access-Control-Allow-Origin", "*");
                .allowedMethods("*") //允许ajax请求哪些方法
                //https://developer.mozilla.org/zh-CN/docs/Web/HTTP/Headers/Access-Control-Allow-Credentials
                .allowCredentials(true)//是否允许携带Credentials 可以是 cookies、authorization headers 或 TLS client certificates。
                .exposedHeaders("*") //设置ajax能获取到响应头哪些头信息 response.setHeader("Access-Control-Expose-Headers","*");
                .allowedHeaders("*");//允许ajax发送哪些自定义(浏览器默认允许所有除预定义的)请求头信息

    }
}
package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author lyq
 * @date 2021/12/4 12:34
 */
@SpringBootApplication
@MapperScan("com.dao")
public class Launcher {


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Launcher.class, args);

    }
}

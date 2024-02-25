package com.a.b.czms;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author lyq
 * @date 2021/12/4 12:34
 */
@SpringBootApplication
public class ZMSApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ZMSApplication.class, args);
    }
}

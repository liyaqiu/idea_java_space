package com.acl.security1;


import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author lyq
 * @date 2021/12/4 12:34
 */
@SpringBootApplication
@Slf4j
public class SecureApplication {


    //FilterSecurityInterceptor 查看过滤链

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SecureApplication.class, args);
    }


}

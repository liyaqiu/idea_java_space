package com;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author lyq
 * @date 2021/12/4 12:34
 */
@SpringBootApplication
@Slf4j
@EnableCaching
public class SpringLauncher {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringLauncher.class, args);

    }


}

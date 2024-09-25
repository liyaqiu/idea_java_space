package com.fm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author lyq
 * @date 2021/12/4 12:34
 */
@SpringBootApplication
public class FreemarkerLauncher {


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(FreemarkerLauncher.class, args);
    }


}

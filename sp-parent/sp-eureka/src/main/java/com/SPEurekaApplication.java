package com;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author lyq
 * @date 2021/12/4 12:34
 */
@SpringBootApplication
@EnableEurekaServer
public class SPEurekaApplication {
    public static void main(String[] args) {
        SpringApplication.run(SPEurekaApplication.class, args);
    }
}

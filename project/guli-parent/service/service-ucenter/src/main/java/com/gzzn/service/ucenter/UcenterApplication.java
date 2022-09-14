package com.gzzn.service.ucenter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author lyq
 * @date 2021/12/4 12:34
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.gzzn.service.ucenter.client")
public class UcenterApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(UcenterApplication.class, args);
    }

}

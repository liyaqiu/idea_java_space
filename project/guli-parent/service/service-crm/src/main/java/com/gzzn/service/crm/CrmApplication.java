package com.gzzn.service.crm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author lyq
 * @date 2021/12/4 12:34
 */
@SpringBootApplication
@EnableDiscoveryClient
public class CrmApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(CrmApplication.class, args);
    }

}

package com.gzzn.service.acl;

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
public class AclApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AclApplication.class, args);
    }

}

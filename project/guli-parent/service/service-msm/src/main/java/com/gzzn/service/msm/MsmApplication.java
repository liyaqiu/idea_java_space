package com.gzzn.service.msm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author lyq
 * @date 2021/12/4 12:34
 */

@SpringBootApplication
@EnableDiscoveryClient
public class MsmApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MsmApplication.class, args);
    }

}

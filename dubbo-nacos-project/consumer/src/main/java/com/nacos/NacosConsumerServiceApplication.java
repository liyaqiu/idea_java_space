package com.nacos;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author lyq
 * @date 2021/12/4 12:34
 */
@EnableDiscoveryClient
@SpringBootApplication
@EnableDubbo
public class NacosConsumerServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(NacosConsumerServiceApplication.class, args);
    }
}

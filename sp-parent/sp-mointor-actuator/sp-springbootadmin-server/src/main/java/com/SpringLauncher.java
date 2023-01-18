package com;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author lyq
 * @date 2021/12/4 12:34
 * spring boot amdin 是对spring actuator的api做管理
 */
@SpringBootApplication
@EnableAdminServer //http://192.168.88.1:9999/applications
public class SpringLauncher {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringLauncher.class, args);
    }


}

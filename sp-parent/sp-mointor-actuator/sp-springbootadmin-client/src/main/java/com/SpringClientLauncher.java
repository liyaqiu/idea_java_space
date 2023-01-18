package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author lyq
 * @date 2021/12/4 12:34
 * 官方文档
 *  https://github.com/codecentric/spring-boot-admin
 * http://127.0.0.1:8888//actuator 查看所有可用的api
 * http://127.0.0.1:8888//actuator/metrics 查看指标
 * http://127.0.0.1:8888//actuator/metrics/jvm.buffer.memory.used  查看具体的指标
 */
@SpringBootApplication
public class SpringClientLauncher {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringClientLauncher.class, args);
    }


}

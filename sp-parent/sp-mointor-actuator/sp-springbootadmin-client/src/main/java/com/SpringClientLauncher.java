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
 *
 * 地址	描述
 * /beans	显示所有的Spring bean列表
 * /caches	显示所有的缓存相关信息
 * /scheduledtasks	显示所有的定时任务相关信息
 * /loggers	显示所有的日志相关信息
 * /configprops	显示所有的配置信息
 * /env	显示所有的环境变量信息
 * /mappings	显示所有控制器相关信息
 * /info	显示自定义用户信息配置
 * /metrics	显示应用指标相关信息
 * /health	显示健康检查状态信息，up表示成功 down表示失败
 * /threaddump	显示程序线程的信息
 */


@SpringBootApplication
public class SpringClientLauncher {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringClientLauncher.class, args);
    }


}

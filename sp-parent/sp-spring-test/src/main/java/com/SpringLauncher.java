package com;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author lyq
 * @date 2021/12/4 12:34
 */
@SpringBootApplication
@MapperScans({@MapperScan("com.mybatisplus"),@MapperScan("com.transaction")})
@Slf4j
//@EnableConfigurationProperties({MyconfigYAML2.class}) 细粒度控制配置类加载
//@EnableScheduling 开启定时任务
public class SpringLauncher {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringLauncher.class, args);
    }

}

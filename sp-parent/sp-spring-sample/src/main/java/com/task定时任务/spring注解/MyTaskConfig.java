package com.task定时任务.spring注解;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author eric
 * @date 2022/5/12 11:54
 **/
@Configuration("HelloTaskTime")
@ConfigurationProperties(prefix = "mytaskconfig")
@Getter
@Setter
public class MyTaskConfig {
    Integer time = 10;
}

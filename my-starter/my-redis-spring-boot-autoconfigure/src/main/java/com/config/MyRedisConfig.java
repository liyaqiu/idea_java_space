package com.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author eric
 * @date 2022/3/18 13:31
 **/
@ConfigurationProperties("myredis")
@Getter
@Setter
@ToString
public class MyRedisConfig {
    /**
     * 主机地址
     * */
    String host;
    /**
     * 主机端口
     * */
    int port;
}

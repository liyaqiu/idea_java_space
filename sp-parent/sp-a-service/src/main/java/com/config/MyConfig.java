package com.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author lyq
 * @date 2022/1/9 0:55
 */
@ConfigurationProperties(prefix = "myconfig")
@Component
@Data
public class MyConfig {
    private String name;
    private String age;
}

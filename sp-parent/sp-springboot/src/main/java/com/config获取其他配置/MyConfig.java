package com.config获取其他配置;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author eric
 * @date 2022/9/23 20:48
 **/
@Configuration
@ConfigurationProperties(prefix = "myconfig")
@Data
public class MyConfig {
    String name;
    String age;
}

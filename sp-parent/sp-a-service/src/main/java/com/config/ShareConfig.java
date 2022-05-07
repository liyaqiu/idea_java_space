package com.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author lyq
 * @date 2022/1/9 0:55
 */
@ConfigurationProperties(prefix = "shareconfig")
@Component
@Data
public class ShareConfig {
    private String shareName;
    private String shareAge;
}

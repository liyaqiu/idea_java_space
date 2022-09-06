package com.gzzn.service.oss.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author eric
 * @date 2022/9/6 17:31
 **/
@Configuration(proxyBeanMethods = false)
@ConfigurationProperties(prefix = "oss-config")
@Data
public class OSSConfig {
    String endpoint;
    String accessKeyId;
    String accessKeySecret;
    String bucketName;
}

package com.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.converter.Converter;

/**
 * @author lyq
 * @date 2022/2/26 23:59
 */
@Configuration
@Import(MyconfigProperties.class)
@ConfigurationProperties(prefix = "myconfig")
@Data
@ToString
public class MyconfigYAML {
    String name;
    String age;
    @Value("${myconfig.path:不存在}")
    String other;


}

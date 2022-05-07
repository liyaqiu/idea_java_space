package com.config;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author lyq
 * @date 2022/2/26 23:59
 */
@Getter
@Setter
@ToString
public class MyconfigYAML1 {
    String name;
    String age;
    String other;

    //此方式也可以通过配置初始化对象
    @Bean
    @ConfigurationProperties("myconfig")
    public MyconfigYAML1 myconfigYAML1(){
        return  new MyconfigYAML1();
    }
}

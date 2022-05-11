package com.AutoConfig;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author eric
 * @date 2022/5/11 16:30
 **/
@ConfigurationProperties(prefix = "mypoolconfig")
@Getter
@Setter
public class MyPoolConfig {
    private CatConfig catConfig;
    private DogConfig dogConfig;
}

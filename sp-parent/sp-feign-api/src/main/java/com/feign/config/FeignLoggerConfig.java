package com.feign.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * @author lyq
 * @date 2022/1/10 2:25
 */
public class FeignLoggerConfig {
    @Bean
    public Logger.Level test0() {
        return Logger.Level.HEADERS;
    }
}

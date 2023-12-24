package com.xss跨站攻击.config;

import com.xss跨站攻击.filter.XSSFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class AntiSamyConfiguration {
    /**
     * 配置跨站攻击过滤器
     * 目前只是对表单进行过滤处理，json形式待测试
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistration =
                new FilterRegistrationBean(new XSSFilter());
        filterRegistration.addUrlPatterns("/*");
        filterRegistration.setOrder(1);
        return filterRegistration;
    }
}

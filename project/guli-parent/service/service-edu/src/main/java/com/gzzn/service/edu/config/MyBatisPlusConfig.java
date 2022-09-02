package com.gzzn.service.edu.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author eric
 * @date 2022/9/2 23:16
 **/
@Configuration
@MapperScan("com.gzzn.service.edu.mapper")
public class MyBatisPlusConfig {

}

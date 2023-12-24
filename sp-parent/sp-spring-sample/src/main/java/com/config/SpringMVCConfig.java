package com.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lyq
 * @date 2022/2/28 6:30
 */
@Configuration
@Slf4j
public class SpringMVCConfig {

   /* @Bean
    public Converter dateConvert11(){
        return new Converter<String, String>() {
            @Override
            public String convert(String s) {
                log.info("String ing...");

                return s+"hello";
            }
        };
    }
    @Bean
    public Converter dateConvert11(){
        return new Converter<String, String>() {
            @Override
            public String convert(String s) {


                return s+"world";
            }
        };
    }*/


    /**
     * 日期转换
     * */
    @Bean
    public Converter dateConvert(){
        return new Converter<String, Date>() {
            @Override
            public Date convert(String s) {
                log.info("时间转换ing...");
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    return format.parse(s);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                return null;
            }
        };
    }
}

package com.sfkj.service.hospital.api;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author eric
 * @date 2022/11/22 19:02
 **/
@SpringBootApplication
public class ServiceHospitalApiApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ServiceHospitalApiApplication.class, args);
    }

}

package com;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lyq
 * @date 2021/12/4 12:34
 */
@SpringBootApplication
//@EnableDiscoveryClient
public class SPBServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SPBServiceApplication.class, args);
    }

    /*@Bean
    public IClientConfig test0(){
        return new DefaultClientConfigImpl();
    }*/

}

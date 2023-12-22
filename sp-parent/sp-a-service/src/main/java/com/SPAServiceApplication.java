package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

/**
 * @author lyq
 * @date 2021/12/4 12:34
 */
@SpringBootApplication
//@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.feign.client")
public class SPAServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SPAServiceApplication.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }



   /* @Bean
    public IClientConfig test0(){
        return new DefaultClientConfigImpl();
    }*/
    /**
     * 旧版本的配法
     * 全局
     * */
/* @Bean
    public IRule test0(){
          return  new RandomRule();
    }*/

    /***
     * 旧版本的配法
     * 针对某个服务进行负载均衡
     * sp-dao:
     *   ribbon:
     *     NFLoadBalancerRuleClassName: com.netflix.loadbalancer.RandomRule
     *
     * */


}

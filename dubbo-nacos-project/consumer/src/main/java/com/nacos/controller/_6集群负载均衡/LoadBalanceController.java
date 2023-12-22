package com.nacos.controller._6集群负载均衡;


import org.apache.dubbo.common.constants.LoadbalanceRules;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service._6集群负载均衡.LoadBalanceService;

@RestController
@RequestMapping("/LoadBalance")
public class LoadBalanceController {

    /**
     * LoadbalanceRules.RANDOM 按权重随机
     * LoadbalanceRules.ROUND_ROBIN 按权重轮询
     * LoadbalanceRules.LEAST_ACTIVE 最少的活跃调用数，相同的活跃随机
     * LoadbalanceRules.CONSISTENT_HASH 一致性HASH，相同参数的请求总是发到同一个提供者
     * */
    @DubboReference(loadbalance = LoadbalanceRules.ROUND_ROBIN)
    private LoadBalanceService loadBalanceService;

    @GetMapping("/test01")
    public String test01() {
        return loadBalanceService.helloWorld("eric");
    }
}

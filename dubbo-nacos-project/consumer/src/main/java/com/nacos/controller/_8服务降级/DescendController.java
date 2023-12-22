package com.nacos.controller._8服务降级;


import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service._1基本使用.HelloWorldService;
import service._8服务降级.DescendService;

@RestController
@RequestMapping("/Descend")
public class DescendController {
    /**
        服务降级3种实现方式
        https://cn.dubbo.apache.org/zh-cn/overview/mannual/java-sdk/advanced-features-and-usage/service/service-downgrade/

         3.配置三
         mock="[fail|force]return|throw xxx"
         fail 或 force 关键字可选，表示调用失败或不调用强制执行 mock 方法，如果不指定关键字默认为 fail
         return 表示指定返回结果，throw 表示抛出指定异常
         xxx 根据接口的返回类型解析，可以指定返回值或抛出自定义的异常
     */
    @DubboReference(timeout = 3000, mock = "return 服务被降级了")
    private DescendService descendService;

    @GetMapping("/test01")
    public String test01() {
        return descendService.helloWorld("eric");
    }
}

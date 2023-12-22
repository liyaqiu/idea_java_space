package com.nacos.controller._3多版本;


import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service._2超时重试.TimeoutAndRetriesService;
import service._3多版本.MultiVersionService;

@RestController
@RequestMapping("/MultiVersion")
public class MultiVersionController {

    @DubboReference(version = "v1.0") //dubbo多版本，可以实现金丝雀发布(灰度发布)
    private MultiVersionService multiVersionServicev1;

    @DubboReference(version = "v2.0") //dubbo多版本，可以实现金丝雀发布(灰度发布)
    private MultiVersionService multiVersionServicev2;

    @GetMapping("/test01")
    public String test01() {
        return multiVersionServicev1.getVersion() + "   "+multiVersionServicev2.getVersion();
    }
}

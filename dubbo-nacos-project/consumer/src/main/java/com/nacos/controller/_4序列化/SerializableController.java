package com.nacos.controller._4序列化;


import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service._4序列化.Person;
import service._4序列化.SerializableService;

@RestController
@RequestMapping("/Serializable")
public class SerializableController {

    @DubboReference
    private SerializableService serializableService;

    @GetMapping("/test01")
    public Person test01() {
        return serializableService.getById(10);
    }
}

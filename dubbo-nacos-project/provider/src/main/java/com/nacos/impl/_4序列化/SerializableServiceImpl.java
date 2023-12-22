package com.nacos.impl._4序列化;

import org.apache.dubbo.config.annotation.DubboService;
import service._4序列化.Person;
import service._4序列化.SerializableService;

@DubboService
public class SerializableServiceImpl implements SerializableService {
    public Person getById(int i){
        return new Person("eric",i);
    }
}

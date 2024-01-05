package com.注解Autowire和resource使用;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author eric
 * @date 2022/9/24 16:37
 **/
@RestController
@RequestMapping("/ResourceAndAutowireController")
public class ResourceAndAutowireController {

    //@Resource
    @Autowired
    List<CustomInter> list;

    @GetMapping("/test")
    public void test(){
        System.out.println(list.size());
    }
}

interface CustomInter{}

@Component
class Custom1 implements CustomInter{}

@Component
class Custom2 implements CustomInter{}



package com.controller;


import cn.bean.HelloUtils;
import com.bean.Goods;
import com.bean.UserBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author lyq
 * @date 2022/2/26 9:21
 */
@RestController  // @Controller+@ResponseBody
@Slf4j
public class TestController {

    //@Autowired(required = false)
    @Autowired(required = true)
    UserBean user;

    @Autowired //根据对象类型匹配
    HelloUtils helloUtils;

    @Autowired
    @Qualifier("goods1") //结合Autowired 根绝bean name名字来获取
    Goods goods;

    @SuppressWarnings("all")
    @Resource(name="goods2") //Resource = Qualifier + Autowired
    Goods goods2;

    @Resource(name="goods")
    Goods goods3;

    @Resource(name="goods4")
    Goods goods4;

    @Value("${myconfig.path:没有路径}")
    String myconfigPath;


    @GetMapping("/test1")
    public String test1(){
        log.debug("user,{}",user);
        log.debug("goods1,{}",goods);
        log.debug("goods2,{}",goods2);
        log.debug("goods3,{}",goods3);
        log.debug("goods4,{}",goods4);
        log.debug("myconfigPath,{}",myconfigPath);

        helloUtils.sayHello();
        return "hello-world";
    }

    @Bean(name = "goods1")
    public Goods bean1(){
         return new Goods("iphone4s","15");
    }

    @Bean(name = "goods2")
    public Goods bean2(){
        return new Goods("iphone7s","2000");
    }

    @Bean
    public Goods goods4(){
        return new Goods("iphone600","5555");
    }



}

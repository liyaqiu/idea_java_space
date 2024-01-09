package com.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/TransController1111")
@Slf4j
public class Primary注解使用 {

    @Autowired
    Comp comp;

    //方式1
//    @Bean
//    public Comp Comp1(){
//        return new Comp1();
//    }
//
//    @Bean
//    @Primary
//    public Comp Comp2(){
//        return new Comp2();
//    }

}

interface Comp{}

//方式2

@Component
class Comp1 implements Comp { }

@Component
@Primary
class Comp2 implements Comp { }

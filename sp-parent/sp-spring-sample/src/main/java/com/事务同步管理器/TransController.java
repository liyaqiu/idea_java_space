package com.事务同步管理器;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronization;
import org.springframework.transaction.support.TransactionSynchronizationManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lyq
 * @date 2021/12/4 12:33
 */
@RestController
@RequestMapping("/TransController")
@Slf4j
public class TransController {


    @GetMapping("/test2")
    @Transactional
    public String test2(){
        TransactionSynchronizationManager.registerSynchronization(new TransactionSynchronization(){
            //事务提交后才执行
            @Override
            public void afterCommit() {
                System.out.println("事务提交后才执行");
            }
        });
        System.out.println("做各种sql CURD操作");
        return "helloworld";
    }


}

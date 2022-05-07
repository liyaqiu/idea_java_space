package com.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @author lyq
 * @date 2022/2/26 9:27
 */
@Slf4j
@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserBean {
    String name;

    //标注为spring bean初始化注解
    @PostConstruct
    public void init(){
        log.info("user init..");
    }

    //标注为spring bean注销注解
    @PreDestroy
    public void destory(){
        log.info("user destory..");
    }
}

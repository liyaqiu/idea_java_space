package com.auto.config.conditional.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author eric
 * @date 2022/3/15 18:34
 **/
@RestController
@Slf4j
@RequestMapping("/condition")
public class ConditionController {

    @Autowired(required = false)
    AutoBean autoBean;

    @Autowired(required = false)
    MyAutoBeanConfig myAutoBeanConfig;

    @GetMapping("/test")
    public void test(){
        log.info("autoBean {}",autoBean);
        log.info("myAutoBeanConfig {}",myAutoBeanConfig);
    }
}

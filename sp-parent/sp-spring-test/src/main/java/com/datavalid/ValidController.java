package com.datavalid;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * @author lyq
 * @date 2022/3/8 11:23
 */
@RestController
@RequestMapping("valid")
@Slf4j
@Validated//加上此注解可以对方法参数做校验
public class ValidController {

    @GetMapping("test1")

    public String test1(@Validated ValidDemo2 user3){
        log.info("test1   {}",user3);
        return "success";
    }


    @GetMapping("test2")
    public String test2(@RequestParam(name = "name",required = true)  @NotNull @Length(min = 5) String name){
        log.info("test2 name:{}",name);
        return "success";
    }

    @Autowired
    ValidDemo3 user4;
    @GetMapping("test3")
    public String test3(){
        log.info("test3 user4:{}",user4);
        return "success";
    }
}

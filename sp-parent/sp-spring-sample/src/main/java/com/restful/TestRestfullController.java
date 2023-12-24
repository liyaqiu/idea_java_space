package com.restful;

import com.bean.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@Slf4j
public class TestRestfullController {



    @GetMapping("/{id}")
    public void test1(@PathVariable("id") String id) {
        log.info("查询用户");
    }

    @PostMapping
    public void test2(@RequestBody UserEntity userEntity) {
        log.info("创建用户");
    }

    @PutMapping("/{id}")
    public void test3(@RequestBody UserEntity userEntity) {
        log.info("修改用户");
    }

    @DeleteMapping("/{id}")
    public void test4(@PathVariable("id") String id) {
        log.info("删除用户");
    }


}

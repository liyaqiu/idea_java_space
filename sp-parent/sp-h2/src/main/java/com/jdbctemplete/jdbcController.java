package com.jdbctemplete;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lyq
 * @date 2022/3/10 10:56
 */
@RestController
@RequestMapping("/jdbc")
@Slf4j
public class jdbcController {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @GetMapping("/test")
    public void test0(){
        String sql = "insert into user_tb values(1,'liyaqiu',23)";
        jdbcTemplate.update(sql);
    }
}

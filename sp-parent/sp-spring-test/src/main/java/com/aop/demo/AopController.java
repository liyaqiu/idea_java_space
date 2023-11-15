package com.aop.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;


@RestController
@RequestMapping("/aop")
@Slf4j
public class AopController {

    @GetUserId("18813664703")
    @GetMapping("/demo")
    public String test1(String userId, HttpServletResponse response) throws IOException {
        log.info("demo {}   {}",userId,response);
        return "demo";
    }

    @GetMapping("/demo1")
    public String test2() throws IOException {
        log.info("demo1");
        return "demo1";
    }


}

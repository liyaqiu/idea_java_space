package com.参数解析器;


import com.参数解析器.annotation.CanshuAnnotation;
import com.参数解析器.entity.Canshu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lyq
 * @date 2022/2/26 9:21
 */
@RestController
@RequestMapping("/canshu")
@Slf4j
public class ArgController {

    @GetMapping("/test1")
   public String test1(@CanshuAnnotation Canshu canshu1, HttpServletRequest request) throws InterruptedException {

       log.info("{} {}",canshu1,request);

       return "参数解析器1";
   }

    @GetMapping("/test2")
    public String test2(Canshu canshu1, HttpServletRequest request) throws InterruptedException {

        log.info("{} {}",canshu1,request);

        return "参数解析器2";
    }

}


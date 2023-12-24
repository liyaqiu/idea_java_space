package com.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.boot.web.server.ErrorPageRegistrar;
import org.springframework.boot.web.server.ErrorPageRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;


@RestController
@RequestMapping("/demo6")
@Slf4j
public class TestController6 {



    @GetMapping("/test1/{type}")
    public void test1(@PathVariable("type") int type) throws Exception {
        if(type==1){
            throw new RuntimeException("类型1");
        }else if(type==2){
            throw new Exception("类型2");
        }
        log.info("test1");
    }

    @GetMapping("/test2")
    public void test2(HttpServletResponse response){
        response.setStatus(400);
        log.info("test2");
    }

//    /**
//     * 根据不同状态走页面跳转 这里有问题待处理
//     * */
//    @Bean
//    public ErrorPageRegistrar errorPage(){
//       return new ErrorPageRegistrar(){
//           @Override
//           public void registerErrorPages(ErrorPageRegistry registry) {
//               log.info("错误页面注册");
//               ErrorPage page1 = new ErrorPage(HttpStatus.NOT_FOUND,"/404.html");
//               ErrorPage page2 = new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR,"/error.html");
//               registry.addErrorPages(page1,page2);
//           }
//       };
//    }



}

package com.gzzn.service.edu.controller;

/**
 * @author eric
 * @date 2022/9/15 14:40
 **/

import com.gzzn.service.common.utils.Res;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@RestController
//@Controller
public class DemoController {

    /*@GetMapping("/test")
    public String test(){
        System.out.println("重定向方式1");
        return "redirect:https://www.baidu.com";
    }*/
    @PostMapping("/test")
    public void test(HttpServletResponse response){
        System.out.println("重定向方式2");
        response.setStatus(302);
        response.setHeader("Location","https://www.baidu.com");
    }

    //http://localhost:8001/edu/chapter?ids=1,2,3
    //http://localhost:8001/edu/chapter?ids=1&ids=2&ids=3
    /*@DeleteMapping()
    @ApiOperation("测试删除")
    public Res test(String[] ids){
        System.out.println(ids);
        //log.debug("removeChapter {} {} {}", ids[0],ids[1],ids[2]);
        return Res.ok();
    }

    @GetMapping("/test1")
    @ApiOperation("请求会跳转到test2")
    public String test1(){
        System.out.println("test1");
        return "redirect:https://www.baidu.com/";
    }

    @GetMapping("/test2")
    @ApiOperation("test2")
    public Res test2(){
        System.out.println("test2");
        return Res.ok().setData("test2");
    }

    public static void main(String[] args) throws UnsupportedEncodingException {
        String baseUrl = "https://www.baidu.com" +
                "?name=%s" +
                "&age=%s";

        String name = "李雅秋";
        String age = "18";
        String formatUrl = String.format(baseUrl, URLEncoder.encode(name, StandardCharsets.UTF_8.toString()), age);
        System.out.println(formatUrl);
        System.out.println(URLDecoder.decode(formatUrl, StandardCharsets.UTF_8.toString()));

    }*/
}

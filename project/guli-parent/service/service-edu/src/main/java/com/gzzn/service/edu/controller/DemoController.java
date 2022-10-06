package com.gzzn.service.edu.controller;

/**
 * @author eric
 * @date 2022/9/15 14:40
 **/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@RestController
@RequestMapping("${myconf.path:/ggggg}")
//@Controller
@Validated
public class DemoController {

    @Value("${myconf.path:rrrrrr}")
    String path;

    @GetMapping("/test1")
    public String test1(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date name){
        System.out.println("test1");
        return "redirect:https://www.baidu.com";
    }

    @PostMapping("/test")
    public void test(HttpServletResponse response){
        System.out.println("重定向方式2");
        response.setStatus(302);
        response.setHeader("Location","https://www.baidu.com");
    }



    @PostMapping("/test3")
    public void test3(){
        System.out.println(path);
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

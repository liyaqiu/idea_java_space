package com.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lyq
 * @date 2021/12/4 12:33
 */
@RestController
public class User2Controller {

   /* @GetMapping("/test")
    public String test2(){
        return "你好";
    }
*/
    @GetMapping("test44")
    public String test3(){
        return "User2Controller helloindex";
    }

}



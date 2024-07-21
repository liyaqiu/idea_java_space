package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author lyq
 * @date 2021/12/4 12:33
 */
@Controller
public class UserController {

    @Autowired
    ServletContext servletContext;

   /* @GetMapping("/test")
    public String test2(){
        return "你好";
    }
*/
    @GetMapping(value = "test3")
    public ModelAndView test3(HttpServletRequest request,HttpSession session){
        ModelAndView model = new ModelAndView("helloindex");
        model.addObject("request1","request1值");
        request.setAttribute("request2","request2值");

        session.setAttribute("session","session值");

        servletContext.setAttribute("application","application值");
        return model;
    }

    @RequestMapping("test4")
    @ResponseBody
    public String test4(Model model){
        model.addAttribute("name","eric");
        return "helloindex";
    }

}



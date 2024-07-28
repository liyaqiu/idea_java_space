package com.fm.controller;


import com.fm.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("")
@Slf4j
public class FmController2 {

    @Autowired
    ServletContext servletContext;

    @GetMapping("/test01")
    public ModelAndView test01(HttpServletRequest request, HttpSession session) throws Exception {
        System.out.println("@@@@@@@@");
        ModelAndView modelAndView = new ModelAndView("hello");
        modelAndView.addObject(new Person("你好 ModelAndView freemarker-project"));

        modelAndView.addObject("request1","request1值");
        request.setAttribute("request2","request2值");

        session.setAttribute("session1","session值");

        servletContext.setAttribute("application1","application值");

        return modelAndView;
    }


    @GetMapping("/test02")
    public String test01(HttpServletRequest request, HttpSession session, Model model) throws Exception {
        System.out.println("!!!!!!!");
        model.addAttribute(new Person("你好 Model freemarker-project"));

        model.addAttribute("request1","request1值");
        request.setAttribute("request2","request2值");

        session.setAttribute("session1","session值");

        servletContext.setAttribute("application1","application值");

        //model.addAttribute("msg","你好 freemarker-project");
        return "hello";
    }
}

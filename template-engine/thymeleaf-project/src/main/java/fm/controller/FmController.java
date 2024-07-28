package fm.controller;


import fm.entity.Person;
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
import java.util.Arrays;
import java.util.Date;

@Controller
@RequestMapping("")
@Slf4j
public class FmController {

    @Autowired
    ServletContext servletContext;

    @GetMapping("/test02")
    public ModelAndView test01(HttpServletRequest request, HttpSession session) throws Exception {
        ModelAndView modelAndView = new ModelAndView("hello");
        modelAndView.addObject(new Person("你好 ModelAndView thymeleaf"));

        modelAndView.addObject("request1","request1值");
        request.setAttribute("request2","request2值");
        session.setAttribute("session1","session值");

        servletContext.setAttribute("application1","application值");
        return modelAndView;
    }

    @GetMapping("/test03")
    public String test01(HttpServletRequest request, HttpSession session, Model model) throws Exception {
        model.addAttribute(new Person("你好 Model thymeleaf"));

        model.addAttribute("request1","request1值");
        request.setAttribute("request2","request2值");

        session.setAttribute("session1","session值");

        servletContext.setAttribute("application1","application值");
        return "hello";
    }


    //常用指令
    @GetMapping("/test04")
    public String test04(HttpServletRequest request, HttpSession session, Model model) throws Exception {
        model.addAttribute("key1","显示文本");
        model.addAttribute("key2","<h2>显示html文本</h2>");

        model.addAttribute(new Person("你好"));
        model.addAttribute("list", Arrays.asList("111","222","333"));

        model.addAttribute("httpAdress", "https://www.baidu.com");
        model.addAttribute("today",new Date());
        return "常用指令";
    }
}

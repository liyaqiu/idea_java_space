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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author lyq
 * @date 2021/12/4 12:33
 */
@Controller
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    ServletContext servletContext;

   /* @GetMapping("/test")
    public String test2(){
        return "你好";
    }
*/
    @GetMapping(value = "test02")
    public ModelAndView test3(HttpServletRequest request,HttpSession session){
        log.error("错误日志33........");
        log.info("错误日志22........");
        log.debug("错误日志11........");

        ModelAndView model = new ModelAndView("helloindex");
        model.addObject("request1","request1值");
        request.setAttribute("request2","request2值");

        session.setAttribute("session","session值");

        servletContext.setAttribute("application","application值");

        /*try {
            if(true){
                throw new RuntimeException("跑异常");
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }*/

        return model;
    }

    @GetMapping(value = "test03")
    public String test3(HttpServletRequest request,HttpSession session,Model model){

        model.addAttribute("request1","request1值");
        request.setAttribute("request2","request2值");

        session.setAttribute("session","session值");

        servletContext.setAttribute("application","application值");
        return "helloindex";
    }




    @RequestMapping("test4")
    @ResponseBody
    public String test4(Model model){
        model.addAttribute("name","eric");
        return "helloindex";
    }

}



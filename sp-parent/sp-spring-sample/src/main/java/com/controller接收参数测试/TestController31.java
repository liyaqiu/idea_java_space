package com.controller接收参数测试;

import cn.hutool.extra.spring.SpringUtil;
import com.bean.TestObj;
import com.bean.UserEntity;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;

/**
 * @author lyq
 * @date 2022/2/26 9:21
 * @RequestBody 接收json数据，默认为表单数据
 * @ResponseBody json数据返回或者字符串返回，默认为转发页面
 */

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController31 {

    @Autowired
    TestObj testObj1;

    @RequestMapping(value = "/test7*", method = {RequestMethod.GET})
    public UserEntity test7(TestObj testObj2) {

        log.info("testObj1：{}", testObj1);
        log.info("testObj1：{}", testObj1.hashCode());

        log.info("testObj2：{}", testObj2);
        log.info("testObj2：{}", testObj2.hashCode());

        log.debug("test7");
        return new UserEntity();
    }


    @Bean(name = "testobj1")
    public TestObj testObj() {
        TestObj testObj = new TestObj("hello", "world");
        return testObj;
    }


    @RequestMapping(value = "/test8", method = {RequestMethod.GET})
    public UserEntity test8(Model model, HttpServletRequest request) {
        request.setAttribute("key1", "value1");
        model.addAttribute("key2", "value1");
        return new UserEntity();
    }

    @RequestMapping(value = "/test9", method = {RequestMethod.GET})
    public ModelAndView test9(Model model, HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("obj", new UserEntity());
        modelAndView.setViewName("/hello1.html");
        return modelAndView;
    }


    // get方式
    //  http://192.168.0.110:8080/test/test10?users[0].name=liyaqiu&users[0].age=31&users[1].name=liqiu&users[1].age=33
    //  http://192.168.0.110:8080/test/test10?users%5B0%5D.name=liyaqiu&users%5B0%5D.age=31&users%5B1%5D.name=liqiu&users%5B1%5D.age=33
    // post方式
    //    <form method="post" action="/test/test10">
    //        <input type="text" name="users[0].name"/>
    //        <input type="text" name="users[0].age"/>
    //        <input type="text" name="users[1].name"/>
    //        <input type="text" name="users[1].age"/>
    //        <input type="submit" value="提交">
    //    </form>
    @RequestMapping(value = "/test10", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public UserPojo test10(UserPojo userPojo /*不支持这种方式List<User> users*/) {
        log.info("test9 {}", userPojo);
        return userPojo;
    }
    //[{"name":"liyaqiu","age":"31"},{"name":"liqiu","age":"33"}]
    @RequestMapping(value = "/test11", method = {RequestMethod.POST})
    @ResponseBody
    public List<User> test11(@RequestBody List<User> users) {
        log.info("test9 {}", users);
        return users;
    }

    //http://192.168.0.110:8080/test/test12?strs=zhangsan&strs=lisi
    @RequestMapping(value = "/test12", method = {RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String[] test12(String[] strs) {
        log.info("test12 {}", Arrays.toString(strs));
        return strs;
    }

    /**
     * RequestParam 参数绑定
     *
     * @RequestParam("name") String username,
     * @RequestParam String username1,
     * @RequestParam(required = false,defaultValue = "默认值"
     */
    @RequestMapping(value = "/test13", method = {RequestMethod.POST, RequestMethod.GET})
    public void test13(@RequestParam(defaultValue = "默认值")  String username) {
        log.info("test13 username:{}",username);
    }


    @Data
    @ToString
    public static class UserPojo {
        List<User> users;

    }

    @Data
    @ToString
    public static class User {
        String name;
        String age;
    }

}

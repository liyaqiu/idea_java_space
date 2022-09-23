package com.config获取其他配置;

import com.common.Result;
import com.controller.JSRequestBodyUser;
import com.controller.JSRequestLineInfo;
import com.entity.User;
import com.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 * @author lyq
 * @date 2021/12/4 12:33
 */
@RestController
@RequestMapping("/config")
@Slf4j
public class ConfigController {

    @Autowired
    MyConfig myConfig;

    @GetMapping("/test2")
    public String test2(){
        log.info("test2执行完成......");
        System.out.println(myConfig);
        return "helloworld";
    }


}

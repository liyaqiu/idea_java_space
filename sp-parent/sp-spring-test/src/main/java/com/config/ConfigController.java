package com.config;

import com.bean.Goods;
import com.bean.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author lyq
 * @date 2022/2/26 9:21
 */
@Controller
@Import(UserEntity.class)
@Slf4j
@RequestMapping("/config")
public class ConfigController {

    @GetMapping("/test2")
    public String test2(){
        log.debug("test2");
        return "/hello1.html"; //返回html或者jsp页面
    }

    @GetMapping("/test4")
    @ResponseBody
    public String test4(){
        log.debug("test4");
        return "@RestController = @Controller + @ResponseBody ";
    }



    @GetMapping("/test5")
    @ResponseBody
    public Goods test5(){
        log.debug("test5");
        return new Goods("ipone8","500");
    }


    @Autowired
    MyconfigYAML myconfigYAML;
    @Autowired
    MyconfigProperties myconfigProperties;

    @GetMapping("/test6")
    @ResponseBody
    public String test6(){
        log.debug("myconfigYAML,{}",myconfigYAML);
        log.debug("myconfigProperties,{}",myconfigProperties);
        return "test6";
    }


    @Autowired
    MyConfig.ConfigBean configBean;
    @Autowired
    MyConfig myConfig;

    @GetMapping("/test7")
    @ResponseBody
    public void test7(){
        log.debug("test7 configBean,{}",configBean.getClass());
        log.debug("test7 myConfig,{}",myConfig.getClass());
    }

    @Autowired
    MyconfigYAML2 myconfigYAML2;

    @GetMapping("/test8")
    @ResponseBody
    public String test8(){
        log.debug("test8 {}",myconfigYAML2);
        return "你好";
    }
}

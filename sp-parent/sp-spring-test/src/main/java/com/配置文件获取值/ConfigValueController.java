package com.配置文件获取值;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author eric
 * @date 2022/9/24 16:37
 **/
@RestController
@RequestMapping("${config.path:/helloworld}")
public class ConfigValueController {

    @Value("${config.path:当前配置文件没有值}")
    String path;

    @GetMapping("/test")
    public void test(){
        System.out.println("配置文件的值"+path);
    }
}

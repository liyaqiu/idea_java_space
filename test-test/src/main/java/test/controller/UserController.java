package test.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author lyq
 * @date 2021/12/4 12:33
 */
@Component
public class UserController {

    //@GetMapping("/test")
    public String test2(){
        return "你好";
    }


    /**
     *
     * */
    @Bean("customObject")
    public Object test() {
        return new Object();
    }
}



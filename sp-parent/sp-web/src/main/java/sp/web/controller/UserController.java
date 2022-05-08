package sp.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import sp.web.entity.UserEntity;

import javax.servlet.http.HttpServletResponse;

/**
 * @author lyq
 * @date 2021/12/4 12:33
 */
@RestController
@Slf4j
public class UserController {


    @PostMapping("/test")
    public void test2(String token ,HttpServletResponse response){
        response.setHeader("token", token);

        log.info("PostMapping");
        log.info("token:{}",token);
    }

    @GetMapping("/test")
    public UserEntity test2(@RequestHeader(required = true) String token,String author){
        log.info("GetMapping");
        log.info("token:{} author:{}",token,author);
        return new UserEntity("liyaqiu","31");
    }

}



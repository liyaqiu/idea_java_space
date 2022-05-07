package web.controller;

import com.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lyq
 * @date 2021/12/4 12:33
 */
@RestController
@Slf4j
public class UserController {

    @Value("${configstr}")
    String configstr;

    @GetMapping("/test")
    public Object test2(){
        UserEntity userEntity =  new UserEntity();
        userEntity.setUsername(configstr);
        userEntity.setPasswd("123456");
        log.info("test2执行完成......");
        return userEntity;
    }


}

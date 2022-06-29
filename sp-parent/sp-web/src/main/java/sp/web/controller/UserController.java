package sp.web.controller;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
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

    @PostMapping("/fanli")
    public UserEntity fanli(@RequestBody UserEntity userEntity){
        log.info("反例测试");
        if(StringUtils.isEmpty(userEntity.getName())){
            userEntity.setName("null");
        }
        if(StringUtils.isEmpty(userEntity.getAge())){
            userEntity.setAge("null");
        }
        log.info("userEntity:{}",userEntity);
        return userEntity;
    }

    @RequestMapping("/test1")
    public String test1(){
        log.info("jmeter-test1");
        byte[] b = new byte[1024*1024*1];
        return "success";
    }
    @RequestMapping("/test2")
    public String test2(){
        log.info("jmeter-test2");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "<a><b><c>eric</c></b></a>";
    }
    @RequestMapping("/test3")
    public String test3(){
        log.info("jmeter-test2");
        return "hanshu1('hello-world')";
    }


    public static void main(String[] args) {
        System.out.println(new Gson().toJson(new UserEntity("eric","31")));
    }
}



package com.异步调用;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lyq
 * @date 2022/2/26 9:21
 */
@RestController
@RequestMapping("/yibu")
//@EnableAsync //一般放在根启动即可
@Slf4j
public class AsyncController {
    @Autowired
    AsyncCompo asyncCompo;

    @GetMapping("/test")
   public String test() throws InterruptedException {
        //class com.异步调用.AsyncCompo$$EnhancerBySpringCGLIB$$4cb639af
        //class com.异步调用.AsyncCompo
       log.info("{}",asyncCompo.getClass());

        asyncCompo.test2();
       return "异步调用";
   }


}

@Component
@Slf4j
class AsyncCompo{
    @Async
    public void test2() throws InterruptedException {
        log.info("执行了方法");
        Thread.sleep(10000);
        System.out.println("你好");
    }
}

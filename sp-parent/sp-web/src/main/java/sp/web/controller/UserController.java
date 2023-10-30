package sp.web.controller;

import cn.hutool.core.collection.ConcurrentHashSet;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import sp.web.entity.UserEntity;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author lyq
 * @date 2021/12/4 12:33
 */
@RestController
@Slf4j
public class UserController {

    @Data
    @AllArgsConstructor
    private static class Person{
        private String id;
        private String name;
        private int age;

    }
    Set<Person> personSet = new ConcurrentHashSet<>();

    @GetMapping("/yidatest")
    public String get(){
        log.info("yidatest get");
        if(personSet.size()==0){
            personSet.add(new Person("1","tom",18));
            personSet.add(new Person("2","jerry",19));
        }
        Map<String,Object> result = new HashMap<>();
        result.put("data", personSet);
        result.put("currentPage",1);
        result.put("totalCount", personSet.size());
        return new Gson().toJson(result);
    }

    @DeleteMapping("/yidatest/{id}")
    public String delete(@PathVariable String id){
        log.info("yidatest delete");

        Map<String,Object> result = new HashMap<>();
        for (Person person : personSet) {
            if(person.getId().equals(id)){
                personSet.remove(person);
            }
        }
        result.put("data", personSet);
        result.put("currentPage",1);
        result.put("totalCount", personSet.size());
        return new Gson().toJson(result);
    }

    @Data
    public static class People{
        private String id;
        private String name;
        private Integer age;
    }
    @PostMapping("/yidatest")
    public String post(@RequestBody People people){
        String s = new Gson().toJson(people);
        log.info("yidatest post {}",s);
        return s;
    }

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

    @GetMapping("/test1")
    public UserEntity test1(){
        log.info("jmeter-test1");
        byte[] b = new byte[1024*1024*1];
        return new UserEntity("liyaqiu","31");
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

}



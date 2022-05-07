package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author lyq
 * @date 2021/12/4 12:34
 */
@SpringBootApplication
@MapperScan("com.dao")
public class Launcher {



    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(Launcher.class, args);




        /*User1Dao userDao = context.getBean(User1Dao.class);
        for (int i = 0; i < 5000000 ; i++) {
            userDao.insert(new MyUser(i+"",i+"name",i));
        }*/
    }
}

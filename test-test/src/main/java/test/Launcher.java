package test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import test.controller.MySpringApplication;

/**
 * @author lyq
 * @date 2021/12/4 12:34
 */
@SpringBootApplication
public class Launcher {

    public static void main(String[] args) {
        //ConfigurableApplicationContext context = SpringApplication.run(Launcher.class, args);
        ConfigurableApplicationContext context = MySpringApplication.run(Launcher.class, args);
    }
}


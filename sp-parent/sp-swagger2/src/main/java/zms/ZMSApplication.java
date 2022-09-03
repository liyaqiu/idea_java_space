package zms;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author lyq
 * @date 2021/12/4 12:34
 */
@SpringBootApplication
@ComponentScan(basePackages = {"xxx.a.b"})
public class ZMSApplication {


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(ZMSApplication.class, args);
    }
}

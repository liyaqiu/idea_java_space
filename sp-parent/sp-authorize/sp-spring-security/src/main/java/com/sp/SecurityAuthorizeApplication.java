package com.sp;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author lyq
 * @date 2021/12/4 12:34
 */
@SpringBootApplication
@Slf4j
public class SecurityAuthorizeApplication {

    static class Son{

    }


    static class Fahter{
        Son son;

        public Son getSon() {
            return son;
        }

        public void setSon(Son son) {
            this.son = son;
        }
    }

    @Bean("aaa")
    //@Scope("prototype")
    public Son test(){
        Son son = new Son();
        System.out.println("test()"+son);
        return son;
    }
    /*@Bean("bbb")
    //@Scope("prototype")
    public Son test1(){
        Son son = new Son();
        System.out.println("test1()"+son);
        return son;
    }*/

    @Bean
    public Fahter test2(){
        Fahter fahter = new Fahter();
        fahter.setSon(test());
        System.out.println("1"+test());
        System.out.println("2"+test());
        System.out.println("3"+test());
        System.out.println("4"+test());
        return fahter;
    }


    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SecurityAuthorizeApplication.class, args);
        //System.out.println(context.getBean("test"));
       /* Fahter fahter = context.getBean(Fahter.class);
        System.out.println(fahter.getSon());*/

    }


}

@Component
class SayHello{
    @Autowired
    public SayHello(SecurityAuthorizeApplication.Son son){
        System.out.println("SayHello"+son);
    }
}

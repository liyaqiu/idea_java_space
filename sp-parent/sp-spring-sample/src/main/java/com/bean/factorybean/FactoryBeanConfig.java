package com.bean.factorybean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author eric
 * @date 2022/5/11 11:54
 * ConfigurableApplicationContext context = SpringApplication.run(SpringLauncher.class, args);
 *         System.out.println(context.getBean(MyFactory.class)); 返回MyFactory对象
 *         System.out.println(context.getBean(MyFactory.class));
 *         System.out.println(context.getBean(MyFactory.class));
 *
 *         System.out.println(context.getBean("myFactory")); 返回MyDog对象
 *         System.out.println(context.getBean("myFactory"));
 *         System.out.println(context.getBean("myFactory"));
 *
 *         System.out.println(context.getBean("myFactory", MyDog.class));
 *         System.out.println(context.getBean("myFactory",MyDog.class));
 *         System.out.println(context.getBean("myFactory",MyDog.class));
 **/
@Configuration
public class FactoryBeanConfig {
    @Bean
    public MyFactory myFactory(){
        return new MyFactory();
    }
}

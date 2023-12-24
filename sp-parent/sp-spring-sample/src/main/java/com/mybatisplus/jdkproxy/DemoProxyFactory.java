package com.mybatisplus.jdkproxy;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

@Component
public class DemoProxyFactory{



    public static void main(String[] args) {
        //System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        //System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");
        UserNameMapper2 userMapper = (UserNameMapper2) new DemoProxyFactory().getProxy();

        System.out.println(UserNameMapper2.class);
        System.out.println(userMapper.getClass());
        userMapper.insert();
        System.out.println("----------------------");
        System.out.println(userMapper.delete("100"));

    }



    @Bean
    public UserNameMapper2 getProxy(){
        return (UserNameMapper2) Proxy.newProxyInstance(UserNameMapper2.class.getClassLoader(), new Class<?>[] {UserNameMapper2.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("jdk代理对象");

                if(args!=null)
                for (int i = 0; i < args.length; i++) {
                    System.out.println(args[i]);
                }

                return 1;
            }
        });
    }


}

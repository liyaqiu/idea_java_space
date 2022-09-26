package com.ThreadLocalTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author eric
 * @date 2022/9/26 23:59
 **/
public class Demo {
    public static void main(String[] args) {
        Secure secure = new Secure();
        ExecutorService service = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            service.execute(()->{
                System.out.println(secure.get().hashCode());
            });
        }
    }
}

class Secure{
    private ThreadLocal<Object> threadLocal = new ThreadLocal(){
        @Override
        protected Object initialValue() {
            System.out.println(Thread.currentThread().getName());
            return new Object();
        }
    };

    public Object get(){
        return threadLocal.get();
    }
}



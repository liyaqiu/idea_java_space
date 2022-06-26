package com;



import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.regex.Pattern;

/**
 * @author eric
 * @date 2022/6/8 16:59
 **/
class Person{

}
class Son1 extends Person{
    public static String aaa1 = "456";
}
public class Test2 {


    public static void main(String[] args) throws IllegalAccessException, InstantiationException {






        //List<String> list = new ArrayList<String>();

        // list.add("123");


       /* Person person = new Person();
        String aaa2 = person.aaa1;*/
        //System.out.println("361234567@qq.com".matches("^[0-9A-z]{5,9}@[a-z]{2}\\.com$"));


        /*ExecutorService service = Executors.newFixedThreadPool(10, new ThreadFactory() {
            int i = 0;

            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r, getThreadName());
                thread.setDaemon(true);
                return thread;
            }

            public String getThreadName() {
                i += 1;
                return "job-" + i;
            }
        });

        for (int i = 0; i < 5; i++) {
            service.execute(new Runnable() {
                @Override
                public void run() {
                    test();
                }
            });
        }*/

    }
    public synchronized static void test(){

    }
}



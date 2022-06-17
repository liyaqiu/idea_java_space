package com;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author eric
 * @date 2022/6/8 16:59
 **/
public class Test2 {
    public static void main(String[] args) {
        //List<String> list = new ArrayList<String>();
        boolean b1 = false;
        boolean b2 = true;
        boolean bl = true||false&&true;
        System.out.println(bl);
       // list.add("123");

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



package com;



import lombok.SneakyThrows;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.regex.Pattern;

/**
 * @author eric
 * @date 2022/6/8 16:59
 **/
class Person{
    public static void haha(){
        System.out.println("father");
    }
}
class Son1 extends Person{
    public static void haha(String name){
        System.out.println("son");
    }
}
public class Test2 {


    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        /*Test2 test = new Test2();
        Thread t1 = new Thread(new Runnable(){
            @SneakyThrows
            @Override
            public void run() {
                while (true){
                    test.test();
                }
            }
        },"t1");
        Thread t2 = new Thread(new Runnable(){
            @SneakyThrows
            @Override
            public void run() {
                while (true){
                    new Scanner(System.in).next();
                    test.test1();
                }
            }
        },"t2");
        t1.start();
        t2.start();*/

    }
    public void test() throws InterruptedException {
        synchronized (Test2.class){
            System.out.println(Thread.currentThread().getName());
            Test2.class.wait(5000);
            System.out.println("我。。。。。");
        }
    }
    public void test1() throws InterruptedException {
        synchronized (Test2.class){
            Test2.class.notifyAll();
            System.out.println("我唤醒");
        }
    }
}



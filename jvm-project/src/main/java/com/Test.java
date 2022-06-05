package com;

import com.ClassLoader.custom.MyClassLoader;
import lombok.SneakyThrows;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.locks.LockSupport;

/**
 * @author eric
 * @date 2022/5/13 11:51
 * -XX:CompileThreshold=1500
 * -XX:-UseCounterDecay
 **/
public class Test implements Serializable {
    static  Object lock1 = new Object();
    static  Object lock2 = new Object();
    /*String s = "123";*/
   // final static String Str1 = new String("hello");
    public static void  main(String[] args) throws InterruptedException, ClassNotFoundException, IllegalAccessException, InstantiationException {
      //-Xms30m -Xmx30m -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError  -XX:HeapDumpPath=D:/abc.hprof
      //jinfo -flag MaxDirectMemorySize 6296
      //新生代 - 100 200
      // 伊甸园区 80  160
      // s0  10  20
      // s1  10  20
      //老年代 - 200 400
        Father father = new Father();
        ArrayList<Class> arr = new ArrayList<>();
        for (int i = 0; i < 200; i++) {
            arr.add(new MyClassLoader().loadClass("com.ClassLoader.custom.PersonEntity"));
        }

        Thread.sleep(Integer.MAX_VALUE);

      /*  new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                testA();
            }
        }).start();

        new Thread(new Runnable() {
            @SneakyThrows
            @Override
            public void run() {
                testB();
            }
        }).start();*/
    }


    public static void testA() throws InterruptedException {
        synchronized(lock1){
            Thread.sleep(1000);
            synchronized(lock2){

            }
        }
    }
    public static void testB() throws InterruptedException {
        synchronized(lock2){
            Thread.sleep(1000);
            synchronized(lock1){

            }
        }
    }



}

class Father{
    byte[] b = new byte[1024*1024*100];
}
class Son extends Father{

}




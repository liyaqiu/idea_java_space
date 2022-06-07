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
    static ArrayList<Class> arr = new ArrayList<>();
    static ArrayList<Father> fathers = new ArrayList<>();
    static ArrayList<Father> fathers1 = fathers;
    static int a = 1000;
    int b =200;
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




        new Scanner(System.in).next();
        fathers.add(new Father());
        fathers.add(new Father());
        fathers.add(new Father());
        fathers.add(new Father());
        new Scanner(System.in).next();
        Test test = new Test();
        Class a = new MyClassLoader().loadClass("com.ClassLoader.custom.PersonEntity");
        Object o = a.newInstance();
        Object o2 = a.newInstance();
        Class b =  new MyClassLoader().loadClass("com.ClassLoader.custom.PersonEntity");
        for (int i = 0; i < 200000; i++) {
            //arr.add(new MyClassLoader().loadClass("com.ClassLoader.custom.PersonEntity"));
            Thread.sleep(1000);
            fathers.add(new Father());
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
    byte[] b = new byte[1024*1024*1];
}
class Son extends Father{

}




package com.对象引用5种;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Scanner;

/**
 * @author eric
 * @date 2022/5/24 9:36
 * -Xms30m -Xmx30m -XX:+PrintGCDetails
 **/
public class 虚引用Test {
    final static ReferenceQueue<Person> referenceQueue = new ReferenceQueue();
    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (true){
                try {
                    System.out.println("发现对象被GC"+referenceQueue.remove());
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();

        PhantomReference<Person> phantomReference = new PhantomReference<Person>(new Person(),referenceQueue);
        //System.out.println(phantomReference.get()); 始终返回Null
        new Scanner(System.in).next();
        System.gc();
        System.out.println("发生1次 gc后");
        new Scanner(System.in).next();
        System.gc();
        System.out.println("发生2次 发生gc后");
        Thread.sleep(20000);
    }
}

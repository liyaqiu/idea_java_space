package com.对象引用5种;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.Scanner;

/**
 * @author eric
 * @date 2022/5/24 9:36
 * -Xms30m -Xmx30m -XX:+PrintGCDetails
 **/
public class 弱引用Test {
    static ReferenceQueue<Person> referenceQueue = new ReferenceQueue();
    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    try {
                        System.out.println("发现对象被GC"+referenceQueue.remove());
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        WeakReference<Person> weakReference = new WeakReference<Person>(new Person(),referenceQueue);
        //以上代码等同于如下代码
        /*
            Person person = new Person();
            WeakReference<Person> weakRef = new WeakReference<Person>(person,new ReferenceQueue());
            person = null;//取消强引用
        */
        System.out.println(weakReference.get());
        new Scanner(System.in).next();
        System.gc();
        System.out.println("发生gc后");
        System.out.println(weakReference.get());
    }
}

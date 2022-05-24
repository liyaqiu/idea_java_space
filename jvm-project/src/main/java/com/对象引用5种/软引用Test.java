package com.对象引用5种;

import javax.lang.model.element.VariableElement;
import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

/**
 * @author eric
 * @date 2022/5/24 9:36
 * -Xms30m -Xmx30m -XX:+PrintGCDetails
 **/
public class 软引用Test {
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



        SoftReference<Person> phantomReference = new SoftReference<Person>(new Person(),referenceQueue);

        //以上代码等同于如下代码
        /*
            Person person = new Person();
            SoftReference<Person> phantomRef = new SoftReference<Person>(person,new ReferenceQueue());
            person = null;//取消强引用
        */
        try {
            //不可以这样操作，这个操作是赋值给了一个强引用
            //Person person = phantomReference.get();
            byte[] bytes = new byte[1024*1024*20];
        }catch (Throwable e){
            e.printStackTrace();
        }finally {
            System.out.println(phantomReference.get());
        }
    }
}

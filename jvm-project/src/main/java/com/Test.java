package com;

import com.ClassLoader.custom.MyClassLoader;

import javax.print.attribute.standard.MediaSize;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.ProtocolException;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author eric
 * @date 2022/5/13 11:51
 * -XX:CompileThreshold=1500
 * -XX:-UseCounterDecay
 **/
public class Test implements Serializable {

    /*String s = "123";*/
   // final static String Str1 = new String("hello");
    public static void  main(String[] args) throws InterruptedException, ClassNotFoundException, IllegalAccessException, InstantiationException {
      //-Xms30m -Xmx30m -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError
      //jinfo -flag MaxDirectMemorySize 6296
      //新生代 - 100 200
      // 伊甸园区 80  160
      // s0  10  20
      // s1  10  20
      //老年代 - 200 400
        new Scanner(System.in).next();
        for (int i = 0; i < 100000 ; i++) {
            new MyClassLoader().loadClass("com.ClassLoader.custom.PersonEntity");
            int b = i;
            System.out.println(b);
        }
        System.out.println("类加载完毕");
        new Scanner(System.in).next();
        System.gc();
        System.out.println("GC完毕");

        Thread.sleep(Integer.MAX_VALUE);

    }


}





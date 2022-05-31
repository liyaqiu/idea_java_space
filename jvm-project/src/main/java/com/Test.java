package com;

import javax.print.attribute.standard.MediaSize;
import java.io.Serializable;
import java.math.BigDecimal;
import java.net.ProtocolException;
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

   /* final static int i = 123;
    final static String str = "123";*/
    public static final  String str1 ;
    static {

        str1 = "123";

    }
    /*String s = "123";*/
   // final static String Str1 = new String("hello");
    public static void  main(String[] args)  throws InterruptedException{
      //-Xms30m -Xmx30m -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError
      //jinfo -flag MaxDirectMemorySize 6296
      //新生代 - 100 200
      // 伊甸园区 80  160
      // s0  10  20
      // s1  10  20
      //老年代 - 200 400
      // List<Object> list = new ArrayList<>();
      /*Integer i = 10;
      Integer j = 20;
      System.out.println(i + j);
      */
       // Thread.sleep(Integer.MAX_VALUE);

       /* System.out.println(Hello.a);
        System.out.println(Hello.str);*/

    }


}


class Hello {

    public static final String str = "hello";
    public static final int a = 123;

    public static  int b = new Integer(1);

    /*static {
        System.out.println("clinit方法执行，类初始化");
    }
    */


    public void test1(){

    }

}


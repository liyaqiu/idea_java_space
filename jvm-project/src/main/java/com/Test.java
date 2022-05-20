package com;

import java.nio.ByteBuffer;
import java.util.Scanner;

/**
 * @author eric
 * @date 2022/5/13 11:51
 * -XX:CompileThreshold=1500
 * -XX:-UseCounterDecay
 **/
public class Test {

   String str = "你哈搜";


   public static void  main(String[] args) throws InterruptedException {
      //-Xms300m -Xmx600m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=100m
      //jinfo -flag MaxDirectMemorySize 6296
      //新生代 - 100 200
      // 伊甸园区 80  160
      // s0  10  20
      // s1  10  20
      //老年代 - 200 400
      ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024 * 1024 * 1024 * 1);
      new Scanner(System.in).next();
      System.out.println("11111");
      new Scanner(System.in).next();
      /*int a = 10;
      String b = "hello";*/
      //new Test();
      byteBuffer = null;
      System.gc();
     // System.out.println(str);
      new Scanner(System.in).next();
   }
}

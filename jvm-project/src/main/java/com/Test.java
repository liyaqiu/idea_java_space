package com;

import java.io.Serializable;

/**
 * @author eric
 * @date 2022/5/13 11:51
 * -XX:CompileThreshold=1500
 * -XX:-UseCounterDecay
 **/
public class Test implements Serializable {

    public static String s = "123";
    final static String s1 = "eeee";


    public static void  main(String[] args) throws InterruptedException {
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
        String s = "123";
    }

    public static class Son{
        public void test(){
            System.out.println(s);
        }
    }

}

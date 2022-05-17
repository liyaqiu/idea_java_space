package com;

/**
 * @author eric
 * @date 2022/5/13 11:51
 **/
public class Test {
   public static void main(String[] args) throws InterruptedException {
      //-Xms300m -Xmx600m -XX:+PrintGCDetails
      //新生代 - 100 200
      // 伊甸园区 80  160
      // s0  10  20
      // s1  10  20
      //老年代 - 200 400
      Thread.sleep(500000);
   }
}

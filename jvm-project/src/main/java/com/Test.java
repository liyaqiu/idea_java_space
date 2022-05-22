package com;

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

   }
}

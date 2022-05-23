package com;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author eric
 * @date 2022/5/13 11:51
 * -XX:CompileThreshold=1500
 * -XX:-UseCounterDecay
 **/
@Slf4j
public class Test {

    public static void test(){
        for (int i = 0; i < 100; i++) {
            byte[] b = new byte[1024*1024*1];
        }
    }

    public static void  main(String[] args) throws InterruptedException {
      //-Xms30m -Xmx30m -XX:+PrintGCDetails -XX:+HeapDumpOnOutOfMemoryError
      //jinfo -flag MaxDirectMemorySize 6296
      //新生代 - 100 200
      // 伊甸园区 80  160
      // s0  10  20
      // s1  10  20
      //老年代 - 200 400
      // List<Object> list = new ArrayList<>();
       new Thread(new Runnable() {
           @SneakyThrows
           @Override
           public void run() {
               while (true){
                   Thread.sleep(1000);
                   log.info("循环: {}","1");
               }
           }
       }).start();

       while (true){
           test();
           new Scanner(System.in).next();
           System.gc();
       }
   }


}

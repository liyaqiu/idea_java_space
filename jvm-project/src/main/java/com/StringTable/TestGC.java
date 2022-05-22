package com.StringTable;

/**
 * @author eric
 * @date 2022/5/22 10:38
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:+PrintStringTableStatistics
 **/
public class TestGC {
    public static void main(String[] args) throws InterruptedException {
        test();
        Thread.sleep(10000);
    }
    public static void test(){
        for (int i = 0; i < 1000000; i++) {
            String.valueOf(i).intern();
        }
    }
}

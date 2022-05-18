package com.逃逸分析;

/**
 * @author eric
 * @date 2022/5/18 14:27
 * 逃逸分析--栈上分配
 * 开启逃逸分析
 *      -Xms256m -Xmx256m -XX:+PrintGCDetails -XX:+DoEscapeAnalysis
 * 关闭逃逸分析
 *      -Xms256m -Xmx256m -XX:+PrintGCDetails -XX:-DoEscapeAnalysis
 **/
public class AnalysisTest {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        for (int i = 0; i < 10000000 ; i++) {
            allocate();
        }

        long end = System.currentTimeMillis() ;
        System.out.println("最终消耗时间: "+(end-start));
    }
    public static void allocate(){
        byte[] b = new byte[10];
    }
}

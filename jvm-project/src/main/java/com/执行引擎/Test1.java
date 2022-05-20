package com.执行引擎;

/**
 * @author eric
 * @date 2022/5/20 17:28
 * -Xint 完全采用解析器模式执行程序
 * -Xcomp 完全采用即时编译器模式执行程序。如果即时编译器出现问题，解析器会接入执行
 * -Xmixed 采用解析器+即时编译器的混合模式共同执行程序 6
 **/
public class Test1 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        test();
        long end = System.currentTimeMillis();
        System.out.println(end-start);
    }

    public static void test(){
        for (int i = 0; i < 100000000; i++) {
            if(i%2==0){
                new Object();
            }else{
                int a = i;
                a++;
            }
        }
    }
}

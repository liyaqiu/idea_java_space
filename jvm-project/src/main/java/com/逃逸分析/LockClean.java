package com.逃逸分析;

/**
 * @author eric
 * @date 2022/5/18 14:53
 * 逃逸分析--锁消除
 **/
public class LockClean {
    public static void main(String[] args) {
        //只有主线程调用到
        test();
    }
    public static void test(){
        synchronized (new LockClean()){
            System.out.println("锁消除");
        }
    }
}

package com.demo2定义函数接口;

import org.junit.Test;

/**
 * @author eric
 * @date 2022/9/25 22:26
 **/
public class Demo2 {

    @Test
    public void test(){
        MyFun<Integer,Long> myFun = (x,y) -> Long.valueOf(x+y);
        System.out.println(myFun.compute(1, 2));
    }
}

@FunctionalInterface
interface MyFun<T,R>{
    R compute(T t1,T t2);
}
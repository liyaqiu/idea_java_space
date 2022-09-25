package com.demo2定义函数接口;

import org.junit.Test;

/**
 * @author eric
 * @date 2022/9/25 22:12
 **/
public class Demo1 {
    @Test
    public void test1(){
        //对字符串增强
        MyFunction<String> myFunction = (str) -> str+=str;
        System.out.println(myFunction.enhancer("我是双重字"));
    }
    @Test
    public void test2(){
        //我要把钱变多
        MyFunction<Integer> myFunction = (money) -> money*money;
        System.out.println(myFunction.enhancer(100));
    }
}


@FunctionalInterface
interface MyFunction<T>{
    T enhancer(T t);
}
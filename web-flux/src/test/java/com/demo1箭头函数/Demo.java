package com.demo1箭头函数;

import org.junit.Test;

import java.util.Collections;
import java.util.Comparator;
import java.util.function.Consumer;

/**
 * @author eric
 * @date 2022/9/25 21:55
 **/
public class Demo {
    //格式一，无参数，1个执行语句
    @Test
    public void test1(){
        Runnable runnable = () -> System.out.println("hello-world");
        runnable.run();
    }
    //格式二，有1个参数，1个执行语句
    @Test
    public void test2(){
        Consumer<String> consumer = (x)  -> System.out.println("这位先生是:"+x);
        consumer.accept("eric");
    }
    //格式三，有1个参数(省略小括号)，1个执行语句
    @Test
    public void test3(){
        Consumer<String> consumer  = x -> System.out.println("这位先生是"+x);
        consumer.accept("jerry");
    }
    //格式四，有多个参数，多个执行语句，且有返回值
    @Test
    public void test4(){
        Comparator<Integer> comparator = (e1,e2) -> {
            e1 += e1;
            e2 += e2;
            return Integer.compare(e1,e2);
        };
        System.out.println(comparator.compare(10, 2));
    }
    //格式五，参数可以写成带变量形式
    @Test
    public void test5(){
        Consumer<String> consumer = (String str) -> System.out.println(str);
        consumer.accept("hello-world");
    }


}
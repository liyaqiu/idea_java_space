package com.demo5方法引用;

import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author eric
 * @date 2022/9/26 13:15
 **/
public class Demo1 {
    /**
     * 类实例对象+非静态方法名
     * */
    @Test
    public void test1(){
        Consumer<Integer> consumer = System.out::println;
        consumer.accept(1000);
    }

    /***
     * 类对象+静态方法名
     * */
    @Test
    public void test2(){
        Function<Double,String> supplier = String::valueOf;
        System.out.println(supplier.apply(500.12D));
    }

    /**
     * 类对象+非静态方法名
     * 前提条件，第一个参数作为对象使用，第二个参数作为参数使用
     * */
    @Test
    public void test3(){
        BiFunction<Integer,Integer,Integer> biFunction = (s1,s2) -> s1.compareTo(s2);
        System.out.println(biFunction.apply(1000, 200));

        BiFunction<Integer,Integer,Integer> function = Integer::compareTo;
        System.out.println(function.apply(2000, 2000));
    }
}

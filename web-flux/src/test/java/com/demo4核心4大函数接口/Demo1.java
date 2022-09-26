package com.demo4核心4大函数接口;

import org.junit.Test;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author eric
 * @date 2022/9/26 12:17
 **/
public class Demo1 {

    /**
     * 有参有无返回值函数
     * */
    @Test
    public void test1(){
        Consumer<String> consumer = (x) -> System.out.println();
        consumer.accept("消费");
    }

    /**
     * 无参有返回值函数
     * */
    @Test
    public void test2(){
        Supplier<Integer> supplier = () -> 1 ;
        System.out.println(supplier.get());
    }
    /**
     * 有参有返回值函数
     * */
    @Test
    public void test3(){
        Function<String,Integer> function = (x) -> Integer.valueOf(x);
        System.out.println(function.apply("1234"));
    }
    /**
     * 断言函数
     * */
    @Test
    public void test4(){
        Predicate<Integer> predicate = (x) -> x == 2000;
        System.out.println(predicate.test(1000));
    }
}

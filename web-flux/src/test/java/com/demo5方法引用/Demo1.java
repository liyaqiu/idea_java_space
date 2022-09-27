package com.demo5方法引用;

import lombok.Data;
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

        BiFunction<Integer,Integer,Integer> function= Integer::compareTo;
        System.out.println(function.apply(2000, 2000));

        //简写
        Function<Person,String> demo11 = (person) ->{
            return person.getName();
        };

        //第一个参数作为对象使用，其他参数根据getName方法一个一个补齐
        Function<Person,String> demo1 = Person::getName;

        //简写
        BiFunction<Person,String,Integer> demo22 = (person,str) ->{
            return person.test(str);
        };
        //第一个参数作为对象使用，其他参数根据test方法一个一个补齐
        BiFunction<Person,String,Integer> demo2 = Person::test;

        //简写
        Consumer<Person> demo33 = (person) ->{
            person.getNa();
        };
        //第一个参数作为对象使用
        Consumer<Person> demo3 = Person::getNa;

        //简写
        AAA<Person,String,String> demo44 = (person,str1,str2) ->{
            person.getNa1(str1,str2);
        };
        //第一个参数作为对象使用，其他参数根据getNa1方法一个一个补齐
        AAA<Person,String,String> demo4 = Person::getNa1;
    }
}

@Data
class Person{
    String name;

    public Integer test(String s1){
        return 1;
    }

    public void getNa(){
        System.out.println("123");
    }

    public void getNa1(String str,String str2){
        System.out.println("123");
    }
}

@FunctionalInterface
interface AAA<T,T2,T3>{
    void apply(T str,T2 str2,T3 str3);
}























package com.demo7构造器引用之数组;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author eric
 * @date 2022/9/26 13:38
 **/
public class Demo {

    /**
     * 数组构造器使用1
     * */
    @Test
    public void test1(){
        //Function<Integer,String[]> function = (length) -> new String[length];
        Function<Integer,String[]> function = String[]::new;
        System.out.println(function.apply(100).length);
    }

    /**
     * 数组构造器使用2
     * */
    @Test
    public void test2(){
        Function<Integer,Person[]> function = Person[]::new;
        System.out.println(function.apply(100).length);
    }
}

class Person{

}

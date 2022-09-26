package com.demo10_接口方法.入门案例;

import org.junit.Test;

/**
 * @author eric
 * @date 2022/9/26 21:43
 **/
public class Demo {
    @Test
    public void test(){
        new Animal(){}.print();
        Animal.hello();
    }
}

interface Animal{
    //默认方法
    default void print(){
        System.out.println("hello-world");
    }
    //静态方法
    static void hello(){
        System.out.println("hello-world");
    }
}
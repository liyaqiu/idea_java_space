package com.demo10_接口方法.接口方法冲突;

import org.junit.Test;

/**
 * @author eric
 * @date 2022/9/26 21:54
 **/
public class Demo2 {
    @Test
    public void test(){
        Son son = new Son();
        son.print();
    }
}



class Son extends Father implements Inter1,Inter2{

}

class Father{
    //可以在子类实现，解决冲突，或者利用父类的实现来解决冲突
    public void print(){
        System.out.println("hello-Father");
    }
}

interface Inter1{
    default void print(){
        System.out.println("hello-inter1");
    }
}

interface Inter2{
    default void print(){
        System.out.println("hello-inter2");
    }
}

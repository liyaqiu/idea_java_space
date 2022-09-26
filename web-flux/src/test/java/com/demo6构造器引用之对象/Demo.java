package com.demo6构造器引用之对象;

import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.Supplier;

/**
 * @author eric
 * @date 2022/9/26 13:31
 **/
public class Demo {
    /**
     * 调用无参构造器
     * */
    @Test
    public void test(){
        Supplier<Person> supplier = Person::new;
        System.out.println(supplier.get());
    }
    /**
     * 调用有参构造器
     * */
    @Test
    public void test2(){
        BiFunction<String,Integer,Person> biFunction = Person::new;
        System.out.println(biFunction.apply("eric", 18));
    }
}

class Person{
    String name;
    Integer age;

    public Person() {

    }
    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
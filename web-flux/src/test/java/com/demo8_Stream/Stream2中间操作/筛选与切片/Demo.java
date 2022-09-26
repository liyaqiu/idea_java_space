package com.demo8_Stream.Stream2中间操作.筛选与切片;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author eric
 * @date 2022/9/26 14:59
 **/
public class Demo {
    List<Person> personList = Arrays.asList(
            new Person("eric19", 19),
            new Person("eric18", 18),
            new Person("eric20", 20),
            new Person("eric21", 21),
            new Person("eric21", 21)
            );
    @Test
    public void test1(){
        Stream<Person> stream = personList.stream()
                .filter((person) -> person.getAge() >= 19) //过滤
                .limit(40) //指定条数
                .skip(2) //跳过多少条
                .distinct();//去重
    }
}

class Person{
    String name;
    Integer age;

    public Person(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return Objects.equals(name, person.name) && Objects.equals(age, person.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
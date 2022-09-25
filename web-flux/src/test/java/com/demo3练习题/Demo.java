package com.demo3练习题;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author eric
 * @date 2022/9/25 22:16
 **/
public class Demo {

    List<Person> personList = Arrays.asList(new Person("jerry",19), new Person("eric",18), new Person("tom",20));

    //按年龄排序，如果年龄相同，则按名字排序
    @Test
    public void test(){
        Collections.sort(personList, (p1,p2) -> {
            if(p1.age ==  p2.age){
                return p1.name.compareTo(p2.name);
            }
            return Integer.compare(p1.age, p2.age);
        });
        personList.stream().forEach(person-> System.out.println(person));
    }


}
class Person{
    String name;
    Integer age;

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
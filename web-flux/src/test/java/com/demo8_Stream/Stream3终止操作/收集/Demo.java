package com.demo8_Stream.Stream3终止操作.收集;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author eric
 * @date 2022/9/26 17:58
 **/
public class Demo {
    List<Person> personList = Arrays.asList(
        new Person("eric19",19, Person.State.FULL),
        new Person("eric20",20, Person.State.FREE),
        new Person("eric21",21, Person.State.FULL),
        new Person("eric22",22, Person.State.FREE)
    );

    @Test
    public void test1(){
        //收集到set集合中
        Set<String> set = personList.stream().map((p) -> p.getName()).collect(Collectors.toSet());
        System.out.println(set);

        //收集到list集合中
        List<String> list = personList.stream().map((p) -> p.getName()).collect(Collectors.toList());
        System.out.println(list);

        //收集到Arraylist集合中
        List<String> arrayList = personList.stream().map((p) -> p.getName()).collect(Collectors.toCollection(ArrayList::new));
        System.out.println(list);
    }


    @Test
    public void test2(){
        //收集并计算总条数
        System.out.println(personList.stream().collect(Collectors.counting()));
        //收集并计算总和
        System.out.println(personList.stream().collect(Collectors.summingInt((p)->p.getAge())));
        //收集并计算最大值
        System.out.println(personList.stream().collect(Collectors.maxBy((p1,p2)->p1.getAge().compareTo(p2.getAge()))));
        //收集并计算最小值
        System.out.println(personList.stream().collect(Collectors.minBy((p1,p2)->p1.getAge().compareTo(p2.getAge()))));
        //收集并计算平均值
        System.out.println(personList.stream().collect(Collectors.averagingInt((p) -> p.getAge())));

        //各种统计统一计算
        System.out.println(personList.stream().collect(Collectors.summarizingInt((p) -> p.getAge())));

    }


    @Test
    public void test3(){
        //分组
        Map<Person.State, List<Person>> collect = personList.stream().collect(Collectors.groupingBy((p) -> p.getState()));
        System.out.println(collect);
        //分区
        Map<Boolean, List<Person>> collect1 = personList.stream().collect(Collectors.partitioningBy((p) -> p.getAge() >= 20));
        System.out.println(collect1);
    }

    @Test
    public void test4(){
        //拼接
        String collect = personList.stream().map(p -> p.getName()).collect(Collectors.joining(","));
        System.out.println(collect);

        //拼接
        String collect1 = personList.stream().map(p -> p.getName()).collect(Collectors.joining(",","<",">"));
        System.out.println(collect1);
    }

}

@Data
@NoArgsConstructor
@AllArgsConstructor
class Person{
    String name;
    Integer age;
    State state;
    enum State{
        FULL,FREE
    }
}

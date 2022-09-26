package com.demo8_Stream.Stream3终止操作.归约;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author eric
 * @date 2022/9/26 17:47
 **/
public class Demo {
    List<Person> personList = Arrays.asList(
            new Person("eric19",19, Person.State.FULL),
            new Person("eric20",20, Person.State.FREE),
            new Person("eric21",21, Person.State.FULL),
            new Person("eric22",22, Person.State.FREE)
    );

    @Test
    public void test(){
        System.out.println(personList.stream().max(Comparator.comparingInt(Person::getAge)));
        System.out.println(personList.stream().map(p -> p.getAge()).max(Integer::compare));
    }

    @Test
    public void test1(){
        //计算总年龄
        Integer reduce = personList.stream().map((p) -> p.getAge())
                .reduce(0, (num1, num2) -> num1 + num2);
        System.out.println(reduce);
    }

    @Test
    public void test2(){
        Integer[] ints = new Integer[]{1,2,3,4,5,6,7};
        Integer reduce = Arrays.stream(ints).reduce(0, Integer::sum);
        System.out.println(reduce);
    }
}


@Data
@AllArgsConstructor
class Person{
    String name;
    Integer age;
    State state;
    enum State{
        FULL,FREE
    }
}
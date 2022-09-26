package com.demo8_Stream.Stream3终止操作.查找与匹配;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * @author eric
 * @date 2022/9/26 17:13
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
        //如何所有元素是否符合条件返回真，否则返回假
        boolean allMatch = personList.stream().allMatch((person) -> person.age > 10);
        System.out.println(allMatch);

        //只要有任意一个元素符合条件返回真，否则为假
        boolean anyMatch = personList.stream().anyMatch((p) -> p.getName().equals("eric"));
        System.out.println(anyMatch);

        //没有任何一个元素匹配则返回真，否则返回假
        boolean noneMatch = personList.stream().noneMatch((p) -> p.getName().equals("eric21"));
        System.out.println(noneMatch);

        //获取第一个元素
        Optional<Person> person = personList.stream().findFirst();
        System.out.println(person);

        //获取任意一个元素
        Optional<Person> person1 = personList.stream().findAny();
        System.out.println(person1);

        //计算元素的总个数
        long count = personList.stream().count();
        System.out.println(count);

        //返回最小值
        Optional<Person> person2 = personList.stream().min(Comparator.comparingInt(Person::getAge));
        System.out.println(person2);

        //返回最大值
        Optional<Person> person3 = personList.stream().max(Comparator.comparingInt(Person::getAge));
        System.out.println(person3);
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

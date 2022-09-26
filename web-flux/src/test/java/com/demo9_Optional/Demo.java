package com.demo9_Optional;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author eric
 * @date 2022/9/26 20:55
 **/
public class Demo {
    @Test
    public void test(){
        //创建一个有值的
        Optional<Person> optional1 = Optional.of(new Person());
        //创建一个空值的
        Optional<Object> optional2 = Optional.empty();
        //创建一个可能有值，可能没值的
        Optional<Person> optional3 = Optional.ofNullable(new Person());
        Optional<Person> optional4 = Optional.ofNullable(null);

        //判断值否存在
        boolean bl = optional1.isPresent();

        //当值不存在则返回默认的
        Person person = optional1.orElse(new Person());
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
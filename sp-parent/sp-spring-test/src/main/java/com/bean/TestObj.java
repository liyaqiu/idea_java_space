package com.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author lyq
 * @date 2022/2/27 23:54
 */
//@Component
@Data
@ToString
public  class TestObj {
    private String name = "liyaqiu";
    private String age = "23";

    private TestObj(){
        System.out.println("TestObj1 init1");
    }

    public TestObj(String name, String age) {
        this.name = name;
        this.age = age;
        System.out.println("TestObj1 init2");
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
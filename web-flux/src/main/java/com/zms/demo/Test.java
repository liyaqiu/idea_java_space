package com.zms.demo;

import java.util.function.Function;

/**
 * @author eric
 * @date 2022/9/26 14:15
 **/
public class Test {
    static <T> Function<T, T> identity() {
        return t -> t;
    }
    public static void main(String[] args) {
        Function<Object, Object> identity = Test.identity();
    }
}

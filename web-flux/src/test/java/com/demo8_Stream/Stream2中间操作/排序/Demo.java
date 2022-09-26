package com.demo8_Stream.Stream2中间操作.排序;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author eric
 * @date 2022/9/26 15:58
 **/
public class Demo {

    @Test
    public void test(){
        Integer[] arr = {4,5,7,1,2,3,6,8,4};
        Stream<Integer> stream = Arrays.stream(arr).sorted();
    }

    @Test
    public void test2(){
        Integer[] arr = {4,5,7,1,2,3,6,8,4};
        Stream<Integer> stream = Arrays.stream(arr).sorted((e1, e2) -> -Integer.compare(e1, e2));
    }
}

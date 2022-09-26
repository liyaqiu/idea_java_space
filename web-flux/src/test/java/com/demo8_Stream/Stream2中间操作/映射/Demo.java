package com.demo8_Stream.Stream2中间操作.映射;

import org.junit.Test;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author eric
 * @date 2022/9/26 15:32
 **/
public class Demo {
    /**
     * map
     * */
    @Test
    public void test1(){
        Short[] arr = new Short[]{1,2,3,4,5,6,7};
        Stream<Integer> stream = Arrays.stream(arr).map((el) -> el + 1);
    }

    /**
     * flatMap
     * */
    @Test
    public void test2(){
        Short[][] arr = new Short[][]{{1,2,3},{4,5,6},{7,8,9}};
        Stream<Short> stream = Arrays.stream(arr).flatMap(Arrays::stream);
    }
}
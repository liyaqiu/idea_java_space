package com.demo8_Stream.Stream3终止操作.打印;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author eric
 * @date 2022/9/26 18:51
 **/
public class Demo {
    @Test
    public void test(){
        String[] arr = new String[]{"张三","李四","王五","赵六","田七"};
        Arrays.stream(arr).forEach(System.out::println);
    }
}

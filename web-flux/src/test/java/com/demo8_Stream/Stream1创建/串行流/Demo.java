package com.demo8_Stream.Stream1创建.串行流;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * @author eric
 * @date 2022/9/26 14:29
 **/
public class Demo {
    @Test
    public void test(){
        //创建普通流
            //方式1
            Stream<String> stream1 = new ArrayList<String>().stream();

            //方式2
            Stream<String> stream2 = Arrays.stream(new String[10]);

            //方式3
            Stream<ArrayList<String>> stream3 = Stream.of(new ArrayList<String>());

        //创建无限流
            //方式1
            Stream<Integer> stream4 = Stream.iterate(10, (x) -> x + 1);
            //方式2
            Stream<Integer> stream5 = Stream.generate(() -> 1);

    }
    

}

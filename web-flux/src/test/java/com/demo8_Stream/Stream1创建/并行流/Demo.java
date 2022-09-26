package com.demo8_Stream.Stream1创建.并行流;

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
    public void test2(){
        //创建并行流，底层用到了JDK原生的 fork join框架
        Stream<String> parallel = new ArrayList<String>().stream().parallel();
        System.out.println(parallel.isParallel());

        //创建串行流，默认为串行
        Stream<String> sequential = new ArrayList<String>().stream().sequential();
        System.out.println(sequential.isParallel());
    }
}

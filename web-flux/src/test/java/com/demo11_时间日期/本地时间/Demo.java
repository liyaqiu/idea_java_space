package com.demo11_时间日期.本地时间;

import org.junit.Test;

import java.time.Duration;
import java.time.LocalTime;

/**
 * @author eric
 * @date 2022/9/27 2:04
 **/
public class Demo {
    @Test
    public void test1(){
        //创建一个指定的本地时间
        LocalTime localTime1 = LocalTime.of(7, 20,12);
        System.out.println(localTime1.toString());

        //创建一个本地时间
        LocalTime localTime2 = LocalTime.now();

        System.out.println(localTime2.toString());

        //比较2个时间的间隔
        Duration duration = Duration.between(localTime2,localTime1);
        System.out.println(duration.toHours());
        System.out.println(duration.toMinutes());
        System.out.println(duration.toMillis());

    }
}

package com.demo11_时间日期.本地日期;

import org.junit.Test;

import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/**
 * @author eric
 * @date 2022/9/27 2:04
 **/
public class Demo {
    @Test
    public void test2(){
        //创建一个指定的本地日期
        LocalDate localDate1 = LocalDate.of(2018, 8, 8);
        System.out.println(localDate1);

        //创建一个本地日期
        LocalDate localDate2 = LocalDate.now();
        System.out.println(localDate2);


        //比较2个日期的间隔
        Period period = Period.between(localDate1, localDate2);
        System.out.println(period.get(ChronoUnit.DAYS));
    }
}

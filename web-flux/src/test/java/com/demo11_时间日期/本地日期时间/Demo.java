package com.demo11_时间日期.本地日期时间;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.HashSet;
import java.util.Locale;

/**
 * @author eric
 * @date 2022/9/27 0:39
 **/
public class Demo {

    @Test
    public void test3(){
        //创建一个指定的本地日期时间
        LocalDateTime oldDateTime = LocalDateTime.of(LocalDate.of(2016, 1, 1), LocalTime.of(8, 8, 8));
        System.out.println(oldDateTime);

        //创建一个本地日期时间
        LocalDateTime newDateTime = LocalDateTime.now();
        System.out.println(newDateTime);

        //指定时区创建本地日期时间
        System.out.println(LocalDateTime.now(ZoneId.of("Asia/Shanghai")));

        //比较2个日期时间的间隔
        Duration between = Duration.between(oldDateTime, newDateTime);
        System.out.println(between.toDays());
        System.out.println(between.toMinutes());
        System.out.println(between.getSeconds());
        System.out.println(between.toMillis());


        LocalDateTime nowDateTime = LocalDateTime.now();
        System.out.println(nowDateTime);
        //计算出后10天的日期
        System.out.println(nowDateTime.plusDays(10));
        //计算出前10天的日期
        System.out.println(nowDateTime.minusDays(10));
    }
    @Test
    public void test(){
        //预定义的格式化
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME));

        //根据指定格式格式化时间
        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分ss秒SSS毫秒",Locale.CHINA)));

        //根据指定格式解析时间
        System.out.println(LocalDateTime.parse("2022年09月27日 01时32分00秒313毫秒", DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH时mm分ss秒SSS毫秒",Locale.CHINA)));

    }
}

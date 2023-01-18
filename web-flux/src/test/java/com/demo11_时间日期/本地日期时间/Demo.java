package com.demo11_时间日期.本地日期时间;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
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
    @Test
    public void test1(){
        //获取今日
        LocalDateTime now = LocalDateTime.now();
        String startDateTime = now.with(LocalTime.MIN).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss",Locale.CHINA));
        String endDateTime = now.with(LocalTime.MAX).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss",Locale.CHINA));
        System.out.println("今日开始时间:" +startDateTime + " 结束时间:" + endDateTime);
        //获取本周
        now = LocalDateTime.now();
        startDateTime = now.with(DayOfWeek.MONDAY).with(LocalTime.MIN).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss",Locale.CHINA));
        endDateTime = now.with(DayOfWeek.SUNDAY).with(LocalTime.MAX).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss",Locale.CHINA));
        System.out.println("本周开始时间:" +startDateTime + " 结束时间:" + endDateTime);
        //获取本月
        now = LocalDateTime.now();
        startDateTime = now.with(TemporalAdjusters.firstDayOfMonth()).with(LocalTime.MIN).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss",Locale.CHINA));
        endDateTime = now.with(TemporalAdjusters.lastDayOfMonth()).with(LocalTime.MAX).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss",Locale.CHINA));
        System.out.println("本月开始时间:" +startDateTime + " 结束时间:" + endDateTime);
        //获取本年
        startDateTime = now.with(TemporalAdjusters.firstDayOfYear()).with(LocalTime.MIN).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss",Locale.CHINA));
        endDateTime = now.with(TemporalAdjusters.lastDayOfYear()).with(LocalTime.MAX).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss",Locale.CHINA));
        System.out.println("本年开始时间:" +startDateTime + " 结束时间:" + endDateTime);
    }
}

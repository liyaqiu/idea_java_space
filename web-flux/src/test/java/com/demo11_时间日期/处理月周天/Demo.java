package com.demo11_时间日期.处理月周天;

import org.junit.Test;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.*;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Demo {
    /**
     * 格式化月
     * */
    @Test
    public void test1() {
        YearMonth thisMonth    = YearMonth.now();

        YearMonth lastMonth    = thisMonth.minusMonths(1);
        YearMonth twoMonthsAgo = thisMonth.minusMonths(2);
        DateTimeFormatter monthYearFormatter = DateTimeFormatter.ofPattern("M");
        //DateTimeFormatter monthYearFormatter = DateTimeFormatter.ofPattern("yyyy MM");

        System.out.printf("当前月: %s\n", thisMonth.format(monthYearFormatter));
        System.out.printf("上个月: %s\n", lastMonth.format(monthYearFormatter));
        System.out.printf("上上个月: %s\n", twoMonthsAgo.format(monthYearFormatter));
    }

    /**
     * 获取月初和月底
     * 2023-09-01T00:00 - 2023-09-30T23:59:59
     * */
    @Test
    public void test2() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime nowStart = now.minusMonths(0).toLocalDate().atTime(0, 0, 0).with(TemporalAdjusters.firstDayOfMonth());
        LocalDateTime nowEnd = now.minusMonths(0).toLocalDate().atTime(23, 59, 59).with(TemporalAdjusters.lastDayOfMonth());
        System.out.println(nowStart);
        System.out.println(nowEnd);

        LocalDateTime lastMonthStart = now.minusMonths(1).toLocalDate().atTime(0, 0, 0).with(TemporalAdjusters.firstDayOfMonth());
        LocalDateTime lastMonthEnd = now.minusMonths(1).toLocalDate().atTime(23, 59, 59).with(TemporalAdjusters.lastDayOfMonth());

        System.out.println(lastMonthStart);
        System.out.println(lastMonthEnd);

        LocalDateTime twoMonthsAgoStart = now.minusMonths(2).toLocalDate().atTime(0, 0, 0).with(TemporalAdjusters.firstDayOfMonth());
        LocalDateTime twoMonthsAgoEnd = now.minusMonths(2).toLocalDate().atTime(23, 59, 59).with(TemporalAdjusters.lastDayOfMonth());

        System.out.println(twoMonthsAgoStart.toString());
        System.out.println(twoMonthsAgoEnd);
    }

    /**
     * 获取周，并且获取这个周的第一天和最后一天
     */
    @Test
    public void test3() {
        // 当前时间
        LocalDateTime now = LocalDateTime.now();
        //这周在今年是第几周
        System.out.println(now.minusWeeks(0).get(WeekFields.ISO.weekOfYear()));
        //这周在今年是第几周，上一周
        System.out.println(now.minusWeeks(1).get(WeekFields.ISO.weekOfYear()));
//        LocalDateTime weekStart = now.minusWeeks(0).toLocalDate().atTime(0, 0, 0).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
//        LocalDateTime weekEnd = now.minusWeeks(0).toLocalDate().atTime(23, 59, 59).with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
//        System.out.println(weekStart);
//        System.out.println(weekEnd);
//
//
//        LocalDateTime weekMinus1Start = now.minusWeeks(1).toLocalDate().atTime(0, 0, 0).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
//        LocalDateTime weekMinus1End = now.minusWeeks(1).toLocalDate().atTime(23, 59, 59).with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
//        System.out.println(weekMinus1Start);
//        System.out.println(weekMinus1End);
//
//        LocalDateTime weekMinus2Start = now.minusWeeks(2).toLocalDate().atTime(0, 0, 0).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
//        LocalDateTime weekMinus2End = now.minusWeeks(2).toLocalDate().atTime(23, 59, 59).with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
//        System.out.println(weekMinus2Start);
//        System.out.println(weekMinus2End);
//
//        LocalDateTime weekMinus3Start = now.minusWeeks(3).toLocalDate().atTime(0, 0, 0).with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
//        LocalDateTime weekMinus3End = now.minusWeeks(3).toLocalDate().atTime(23, 59, 59).with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
//        System.out.println(weekMinus3Start);
//        System.out.println(weekMinus3End);
//

    }

    /**
     * 处理天
     * */
    @Test
    public void test4() {
        LocalDateTime now = LocalDateTime.now();


        for (int i = 6; i >= 0 ; i--) {
            System.out.println(now.minusDays(i).format(DateTimeFormatter.ofPattern("M/d日")));
            System.out.println(now.minusDays(i).toLocalDate().atTime(0, 0, 0));
            System.out.println(now.minusDays(i).toLocalDate().atTime(23, 59, 59));
        }


        //这周在今年是第几周
//        System.out.println(now.minusWeeks(0).get(WeekFields.ISO.weekOfYear()));
//        System.out.println(now.minusWeeks(1).get(WeekFields.ISO.weekOfYear()));
//        System.out.println(now.minusWeeks(2).get(WeekFields.ISO.weekOfYear()));
//        System.out.println(now.minusWeeks(3).get(WeekFields.ISO.weekOfYear()));

    }


    /**
     *
     * */
    @Test
    public void test() {
//        BigDecimal x = new BigDecimal(3);
//        BigDecimal y = new BigDecimal(10);
//        System.out.println(x.divide(y,3,BigDecimal.ROUND_HALF_UP).doubleValue());
//
//        BigDecimal bd = new BigDecimal("3.1445926");
//        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
//        System.out.println(bd);   //输出结果为3.14

//        LocalDateTime now = LocalDateTime.now().plusDays(1);
//        System.out.println(now);
//        System.out.println(now.plusDays(1));
//        System.out.println(now.plusHours(24));
//
//        int[] a = new int[]{1,23,4,5};
//        System.out.println(Arrays.stream(a).sum()+Arrays.stream(a).sum());
       // System.out.println(LocalDate.parse("2023-08-04", DateTimeFormatter.ofPattern("yyyy-MM-dd")));
       // System.out.println(LocalDate.parse("2023-08-04", DateTimeFormatter.ofPattern("yyyy-MM-dd")).format(DateTimeFormatter.ofPattern("M月d日")));


        // 7 - 10
        // 8

        LocalDateTime testTime = LocalDateTime.parse("2023-10-01 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        //LocalDateTime testTime = LocalDateTime.parse("2023-10-06 00:00:00", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

//        LocalDateTime startTime = getMonthStartTime(LocalDateTime.now(),0);
//        LocalDateTime endTime = getMonthEndTime(LocalDateTime.now(),0);
//        System.out.println(testTime);
//        System.out.println(startTime);
//        System.out.println(endTime);
//        System.out.println(testTime.compareTo(startTime));
//        System.out.println(testTime.compareTo(endTime));
//        System.out.println( testTime.compareTo(startTime)>=0 && testTime.compareTo(endTime)<=0);

        System.out.println(LocalDateTime.parse("2023-10-01T00:00"));

    }

    private LocalDateTime getMonthStartTime(LocalDateTime dateTime, int minus) {
        return dateTime.minusMonths(minus).toLocalDate().atTime(0, 0, 0).with(TemporalAdjusters.firstDayOfMonth());
    }

    private LocalDateTime getMonthEndTime(LocalDateTime dateTime, int minus) {
        return dateTime.minusMonths(minus).toLocalDate().atTime(23, 59, 59).with(TemporalAdjusters.lastDayOfMonth());
    }

    @Test
    public void test11() {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(getMonthStartTime(now, 2));

        System.out.println(getMonthEndTime(now, 0));
    }
}

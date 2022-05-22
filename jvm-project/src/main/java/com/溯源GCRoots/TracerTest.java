package com.溯源GCRoots;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * @author eric
 * @date 2022/5/22 16:29
 **/
public class TracerTest {
    public static void main(String[] args) throws InterruptedException {
        List<Object> numList = new ArrayList<>();
        Date birth = new Date();
        for (int i = 0; i < 100; i++) {
            numList.add(String.valueOf(i));
            Thread.sleep(10);
        }

        System.out.println("数据加载完毕");
        new Scanner(System.in).next();
        numList = null;
        birth = null;

        System.out.println("numList,birth已经置空,请操作");
        new Scanner(System.in).next();
        System.out.println("结束");
    }
}

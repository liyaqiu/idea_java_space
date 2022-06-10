package com.Heap;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

/**
 * @author eric
 * @date 2022/5/17 11:22
 * -Xms300m -Xmx300m
 * 获取堆区的一些参数
 **/
@Slf4j
public class GetHeapTest {
    @Test
    public void test(){
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
        System.out.println("堆初始化大小 "+heapMemoryUsage.getInit() / 1024 / 1024 + "m");
        System.out.println("堆最大大小 "+heapMemoryUsage.getMax() / 1024 / 1024 + "m");
        System.out.println("堆使用大小 "+heapMemoryUsage.getUsed() / 1024 / 1024 + "m");
        System.out.println(memoryMXBean.getHeapMemoryUsage());
        System.out.println(memoryMXBean.getNonHeapMemoryUsage());

        //当前堆的总大小
        long totalMemory = Runtime.getRuntime().totalMemory() / 1024 /1024;
        //当前堆最大内存大小
        long maxMemory = Runtime.getRuntime().maxMemory() / 1024 /1024;
        //当前堆空闲内存大小
        long freeMemory = Runtime.getRuntime().freeMemory()/1024/1024;
    }
}

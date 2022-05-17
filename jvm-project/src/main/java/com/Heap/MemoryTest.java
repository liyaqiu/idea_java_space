package com.Heap;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author eric
 * @date 2022/5/17 11:22
 * -Xms300m -Xmx300m
 **/
@Slf4j
public class MemoryTest {
    @Test
    public void test(){
        //堆初始化内存大小
        long initSize = Runtime.getRuntime().totalMemory() / 1024 /1024;
        //堆总内存大小
        long maxSize = Runtime.getRuntime().maxMemory() / 1024 /1024;
        log.info("initSize: {}{}  maxSize: {}{}",initSize,"m",maxSize,"m");

    }
}

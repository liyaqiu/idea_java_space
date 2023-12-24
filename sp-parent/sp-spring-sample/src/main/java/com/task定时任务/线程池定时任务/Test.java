package com.task定时任务.线程池定时任务;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Test {

    public void test(){
        ScheduledExecutorService service = Executors.newScheduledThreadPool(2);
        /*service.schedule(() -> {

        },5,TimeUnit.SECONDS );*/

        service.scheduleAtFixedRate(() -> {

        },5,5,TimeUnit.SECONDS );
    }
}

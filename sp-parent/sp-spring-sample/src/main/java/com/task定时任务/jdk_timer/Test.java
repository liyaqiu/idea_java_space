package com.task定时任务.jdk_timer;

import java.util.Timer;
import java.util.TimerTask;

public class Test {

    public void test(){
        TimerTask timerTask = new TimerTask(){
            @Override
            public void run() {

            }
        };
        Timer timer = new Timer();
        //timer.schedule(timerTask,2000);
        timer.schedule(timerTask,2000,5000);
    }
}

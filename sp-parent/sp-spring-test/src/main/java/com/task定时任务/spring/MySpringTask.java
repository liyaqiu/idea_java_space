package com.task定时任务.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author lyq
 * @date 2022/3/12 10:33
 * @EnableScheduling //开启spring 任务调度
 * public class SpringLauncher {
 */
@Component
//@EnableScheduling
@Slf4j
public class MySpringTask {


    //在线生成corn表达式 https://cron.qqe2.com/
    //@Scheduled(cron = "0/3 * * * * ?")
    //@Scheduled(cron = "0/${MyTaskConfig.time2:1} * * * * ?")//獲取yaml文件的值
    @Scheduled(cron = "0/#{HelloTaskTime.time} * * * * ?")//根据容器bean name获取值
    public void testTask(){
        log.info("spring task running...");
    }
}

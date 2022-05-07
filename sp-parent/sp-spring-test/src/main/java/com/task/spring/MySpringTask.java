package com.task.spring;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author lyq
 * @date 2022/3/12 10:33
 * @EnableScheduling //开启spring 任务调度
 * public class SpringLauncher {
 */
@Component
@Slf4j
public class MySpringTask {

    @Scheduled(cron = "0/3 * * * * ?")
    public void testTask(){
        log.info("spring task running...");
    }
}

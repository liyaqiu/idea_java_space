package com.task定时任务.quartz;

import org.quartz.*;
import org.springframework.context.annotation.Bean;

/**
 * @author lyq
 * @date 2022/3/12 10:10
 */
//@Configuration(proxyBeanMethods = true)
public class QuartzConfig {

    @Bean
    public JobDetail jobDetail(){
         return JobBuilder.newJob(QuartzTask.class).storeDurably().build();
    }
    @Bean
    public Trigger cronTrigger(){
        CronScheduleBuilder schedule = CronScheduleBuilder.cronSchedule("0/3 * * * * ?");
        return TriggerBuilder.newTrigger().forJob(jobDetail()).withSchedule(schedule).build();
    }
}

package com.task.quartz;

import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

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

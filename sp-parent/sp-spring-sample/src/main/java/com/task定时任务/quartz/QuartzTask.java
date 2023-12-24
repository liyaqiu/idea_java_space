package com.task定时任务.quartz;


import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author lyq
 * @date 2022/3/12 10:04
 */
@Slf4j
public class QuartzTask extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("quartz task running ...");
    }
}

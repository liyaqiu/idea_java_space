package com.xxljob.task._1bean模式;

import com.xxl.job.core.biz.model.ReturnT;
import com.xxl.job.core.handler.annotation.XxlJob;
import com.xxl.job.core.log.XxlJobLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TaskDemo {

    @Value("${server.port}")
    int serverPort;

    @XxlJob("helloWorld")
    public ReturnT<String> helloWorld(String param) throws Exception {
        XxlJobLogger.log("XXL-JOB, Hello World."+serverPort+" param  "+param);
        System.out.println("XXL-JOB, Hello World."+serverPort+" param  "+param);
        return ReturnT.SUCCESS;
    }
}

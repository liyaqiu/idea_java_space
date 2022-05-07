package com.controller;

import com.utils.MetricUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author eric
 * @date 2022/3/15 13:06
 **/
@RestController
@Slf4j
public class TestController {

    @Autowired
    MetricUtils metricUtils;


    @GetMapping("/test/*")
    public void test(){
        log.debug("test {}","debug");
        log.info("test {}","info");
        log.error("test {}","error");
        metricUtils.getCounter().increment();
    }
}

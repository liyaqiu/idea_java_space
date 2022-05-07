package com.utils;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author eric
 * @date 2022/3/15 16:38
 **/
@Component
@Slf4j
@Getter
public class MetricUtils {

    private Counter counter;



    public MetricUtils(@Autowired  MeterRegistry meterRegistry){
        super();
        log.info("MetricUtils(MeterRegistry meterRegistry)");
        counter = meterRegistry.counter("myMtreic");
    }




}

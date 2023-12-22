package com.nacos.impl._3多版本;

import org.apache.dubbo.config.annotation.DubboService;
import service._3多版本.MultiVersionService;

@DubboService(version = "v1.0")
public class MultiVersionServiceV1Impl implements MultiVersionService {
    @Override
    public String getVersion() {
        return "v1.0";
    }
}

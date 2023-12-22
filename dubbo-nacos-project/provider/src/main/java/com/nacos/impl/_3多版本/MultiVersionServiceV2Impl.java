package com.nacos.impl._3多版本;

import org.apache.dubbo.config.annotation.DubboService;
import service._3多版本.MultiVersionService;

@DubboService(version = "v2.0")
public class MultiVersionServiceV2Impl implements MultiVersionService {
    @Override
    public String getVersion() {
        return "v2.0";
    }
}

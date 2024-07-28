package com.gzzn.service.ucenter.client.impl;

import com.gzzn.service.ucenter.client.ServiceMsmClient;
import com.gzzn.service.utils.Res;
import org.springframework.stereotype.Component;

/**
 * @author eric
 * @date 2022/9/12 14:34
 **/
@Component
public class ServiceMsmClientImpl implements ServiceMsmClient {
    @Override
    public Res verifyCode(String mobile, String code) {
        return null;
    }
}

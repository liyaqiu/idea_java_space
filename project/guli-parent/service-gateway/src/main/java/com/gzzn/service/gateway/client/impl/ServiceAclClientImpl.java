package com.gzzn.service.gateway.client.impl;

import com.gzzn.service.gateway.client.ServiceAclClient;
import com.gzzn.service.utils.Res;
import org.springframework.stereotype.Component;

/**
 * @author eric
 * @date 2022/9/12 14:34
 **/
@Component
public class ServiceAclClientImpl implements ServiceAclClient {

    @Override
    public Res queryAuthoritiesByUsername(String username) {
        return null;
    }
}

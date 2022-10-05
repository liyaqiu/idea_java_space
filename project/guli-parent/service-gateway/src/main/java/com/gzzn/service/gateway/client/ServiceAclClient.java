package com.gzzn.service.gateway.client;

import com.gzzn.service.gateway.client.impl.ServiceAclClientImpl;
import com.gzzn.service.utils.Res;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author lyq
 * @date 2022/1/9 18:58
 */
@FeignClient(value = "service-acl",fallback = ServiceAclClientImpl.class)
public interface ServiceAclClient {

    /**
     * 根据用户查询权限以及用户信息
     * */
    @GetMapping("/acl/permission/{username}")
    public Res queryAuthoritiesByUsername(@PathVariable("username") String username);
}



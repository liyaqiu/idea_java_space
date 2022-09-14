package com.gzzn.service.ucenter.client;

import com.gzzn.service.common.utils.Res;
import com.gzzn.service.ucenter.client.impl.ServiceMsmClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * @author lyq
 * @date 2022/1/9 18:58
 */
@FeignClient(value = "service-msm",fallback = ServiceMsmClientImpl.class)
public interface ServiceMsmClient {

    /*校验验证码*/
    @GetMapping("/msm/verify/code")
    public Res verifyCode(@RequestHeader("mobile")  String mobile, @RequestHeader("code") String code);
}



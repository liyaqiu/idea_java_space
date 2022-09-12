package com.gzzn.service.edu.client;

import com.gzzn.service.common.utils.Res;
import com.gzzn.service.edu.client.impl.ServiceOssClientImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author lyq
 * @date 2022/1/9 18:58
 */
@FeignClient(value = "service-oss",fallback = ServiceOssClientImpl.class)
public interface ServiceOssClient {
    /*视频删除*/
    @DeleteMapping("/oss/video/{filename}")
    public Res removeVideo(@PathVariable("filename") String objectName);

}



package com.feign.client;

import com.entity.UserEntity;
import com.feign.client.sentinel.UserClientFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author lyq
 * @date 2022/1/9 18:58
 */
@FeignClient(value = "sp-b-service",fallbackFactory = UserClientFallbackFactory.class)
public interface UserClient {
    @GetMapping("/selectUser/{id}")
    public UserEntity selectUser(@PathVariable String id);

}



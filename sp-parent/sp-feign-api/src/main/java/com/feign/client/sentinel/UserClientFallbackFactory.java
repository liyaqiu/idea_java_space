package com.feign.client.sentinel;

import com.entity.UserEntity;
import com.feign.client.UserClient;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * sentinel熔断降级返回处理
 * @author lyq
 * @date 2022/1/21 4:12
 */
@Slf4j
@Component
public class UserClientFallbackFactory implements FallbackFactory<UserClient> {
    @Override
    public UserClient create(Throwable throwable) {
        return new UserClient() {
            @Override
            public UserEntity selectUser(String id) {
                log.error("查询失败", throwable);
                return new UserEntity();
            }
        };
    }
}

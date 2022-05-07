package feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author lyq
 * @date 2022/1/9 18:58
 */
@FeignClient(value = "sp-cc-service")
public interface CCServiceClient {
    @GetMapping("/cctest/{id}")
    public void cctest(@PathVariable String id);
}



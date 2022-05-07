package sp.caffeine;

import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.HashSet;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * caffeine本地缓存测试
 * @author lyq
 * @date 2022/2/8 15:10
 */
@Slf4j
public class CaffeineTest {
    @Test
    public void test1(){
        Cache<String, String> cache = Caffeine.newBuilder()
                .expireAfterWrite(1000, TimeUnit.SECONDS)
                .build();
        cache.put("hello-world", "caffeine本地缓存");

        String value = cache.getIfPresent("hello-world");
        log.info("value:{}",value);
    }
    @Test
    public void test2(){
        Cache<String, String> cache = Caffeine.newBuilder().build();
        cache.put("hello-world", "caffeine本地缓存");
        String value = cache.get("hello-non", new Function<String, String>() {
            @Override
            public String apply(String s) {
                log.info("走数据。。。。。。");
                return "从数据库查到的结果";
            }
        });
        log.info("value:{}",value);
        log.info("value:{}",value);
    }
    /***
     * 缓存驱逐策略
     * 在默认情下，当一个缓存元素过期的时候，Caffeine不会自动立即将其清理，
     * 而是在一次读过这写操作后，或者在空闲时间完成对失效的数据进行清除
     * */
    @Test
    public void test3(){
        Cache<String, String> cache = Caffeine.newBuilder()
                .initialCapacity(100)
                .maximumSize(1000)//超过1000以后会保留热数据，其他都会清除
                .build();
        Cache<String, String> cache1 = Caffeine.newBuilder()
                .initialCapacity(100)
                .expireAfterWrite(Duration.ofSeconds(10))//超过10秒未访问会清除
                .build();

    }
}

import cn.hutool.json.JSONUtil;
import io.lettuce.core.KeyScanCursor;
import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.util.HashMap;
import java.util.List;

public class Demo {
    RedisCommands<String, String> commands;
    RedisClient redisClient;
    StatefulRedisConnection<String, String> connect;
    @Before
    public void before_test() {
        RedisURI redisURI = RedisURI.builder()
                .redis("192.168.88.11")
                .withPassword("123456")
                .withPort(6661).build();
        RedisClient redisClient = RedisClient.create(redisURI);
        StatefulRedisConnection<String, String> connect = redisClient.connect();
        RedisCommands<String, String> commands = connect.sync();
        this.redisClient = redisClient;
        this.connect = connect;
        this.commands = commands;
    }

    @After
    public void after_test() {
       this.connect.close();
       this.redisClient.shutdown();
    }


    @Data
    @AllArgsConstructor
    class Person {
        private String name;
        private Integer age;
    }

    @Test
    public void String类型() {
        commands.set("k1", JSONUtil.toJsonStr(new Person("eric", 18)));
        String k1 = commands.get("k1");
        System.out.println(k1);
    }

    @Test
    public void List类型() {
        commands.rpush("k2", "1");
        commands.rpush("k2", "2");
        commands.rpush("k2", "3");
        System.out.println(commands.lrange("k2", 0, -1));
        System.out.println(commands.rpop("k2"));
    }

    @Test
    public void Hash类型() {
        commands.hset("k3", new HashMap<String, String>() {
            {
                put("name", "eric");
                put("age", "18");
                put("sex", "男");
            }
        });
        System.out.println(commands.hget("k3", "name"));
    }

    @Test
    public void Set类型() {
        commands.sadd("k4", "1", "2", "1", "3");
        System.out.println(commands.smembers("k4"));
    }

    @Test
    public void Sorted_Set类型() {
        commands.zadd("k5", 10.4D, "33");
        commands.zadd("k5", 10.2D, "11");
        commands.zadd("k5", 10.3D, "22");
        commands.zrem("k5", "1");
        System.out.println(commands.zrange("k5", 0, -1));
    }

}

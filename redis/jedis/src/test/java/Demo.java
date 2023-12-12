import cn.hutool.Hutool;
import cn.hutool.json.JSONUtil;
import jdk.nashorn.internal.scripts.JD;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

import java.util.HashMap;

public class Demo {
    Jedis jedis;
    @Before
    public void before_test(){
        Jedis jedis = new Jedis("192.168.88.11", 7771);
        jedis.auth("123456");
        this.jedis = jedis;
    }


    @Data
    @AllArgsConstructor
    class Person{
        private String name;
        private Integer age;
    }
    @Test
    public void String类型(){
        jedis.set("k1", JSONUtil.toJsonStr(new Person("eric",18)));
        String k1 = jedis.get("k1");
        System.out.println(k1);
    }

    @Test
    public void List类型(){
        jedis.rpush("k2","1");
        jedis.rpush("k2","2");
        jedis.rpush("k2","3");
        System.out.println(jedis.lrange("k2", 0, -1));
        System.out.println(jedis.rpop("k2"));
    }

    @Test
    public void Hash类型(){
        jedis.hset("k3",new HashMap<String,String>(){
            {
                put("name","eric");
                put("age","18");
                put("sex","男");
            }
        });
        System.out.println(jedis.hget("k3", "name"));
    }

    @Test
    public void Set类型(){
        jedis.sadd("k4","1","2","1","3");
        System.out.println(jedis.smembers("k4"));
    }

    @Test
    public void Sorted_Set类型(){
        jedis.zadd("k5",10.4D,"33");
        jedis.zadd("k5",10.2D,"11");
        jedis.zadd("k5",10.3D,"22");
        jedis.zrem("k5","1");
        System.out.println(jedis.zrange("k5", 0, -1));
    }
}

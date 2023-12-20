package com.gzzn.案例._2距离计算;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

@RestController
@RequestMapping("/GEO")
public class GeoController {

    public static void main(String[] args) {
        System.out.println("柳柴".hashCode());
        System.out.println("柴柕".hashCode());
    }
    private final String KEY = "GEO";
    @Autowired
    private RedisTemplate redisTemplate;

    //初始化数据
    @PostConstruct
    public void initData(){
        redisTemplate.opsForGeo().add(KEY,new Point(116.403963,39.915115),"天安门");
        redisTemplate.opsForGeo().add(KEY,new Point(116.403414,39.924091),"故宫");
    }

    //查看某个地址的坐标
    @GetMapping("test01")
    public void test01(){
        List<Point> position = redisTemplate.opsForGeo().position(KEY, "天安门");
        System.out.println(position);
    }

    //查看2个地址的距离
    @GetMapping("test02")
    public void test02(){
        Distance distance = redisTemplate.opsForGeo().distance(KEY, "天安门", "故宫", Metrics.KILOMETERS);
        System.out.println(distance);
    }

    //查找某个位置10公里内的
    @GetMapping("test03")
    public void test03(){
        Distance distance = new Distance(10,Metrics.KILOMETERS);
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeCoordinates().includeDistance().sortAscending().limit(10);
        GeoResults geoResults = redisTemplate.opsForGeo().radius(KEY, "天安门", distance, args);
        List<GeoResult> content = geoResults.getContent();
        System.out.println(content);
    }
    //查找坐标位置10公里内的
    @GetMapping("test04")
    public void test04(){
        RedisGeoCommands.GeoRadiusCommandArgs args = RedisGeoCommands.GeoRadiusCommandArgs.newGeoRadiusArgs().includeCoordinates().includeDistance().sortAscending().limit(10);
        Circle circle = new Circle(new Point(116.403963,39.915115),new Distance(10,Metrics.KILOMETERS));
        GeoResults geoResults = redisTemplate.opsForGeo().radius(KEY,circle , args);
        List<GeoResult> content = geoResults.getContent();
        System.out.println(content);
    }
}

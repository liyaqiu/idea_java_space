package com.controller;

import com.github.benmanes.caffeine.cache.Cache;
import com.entity.Goods;
import com.entity.GoodsStock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.function.Function;

/**
 * @author lyq
 * @date 2022/2/20 1:14
 */
@Slf4j
@RestController
public class TestController {

    @Autowired
    Cache<String, Goods> goodsCache;
    @Autowired
    Cache<String, GoodsStock> stockCache;


    @GetMapping("/goods/{id}")
    public Goods test1(@PathVariable String id){
        Goods goods = goodsCache.get(id, new Function<String, Goods>() {
            @Override
            public Goods apply(String s) {
                log.info("goods数据库。。。。。。");
                return MyContext.goodsMap.get(s);
            }
        });
        log.info("goods。。。。。。");
        return goods;
    }
    @GetMapping("/goods/stock/{id}")
    public GoodsStock test2(@PathVariable String id){
        GoodsStock goodsStock = stockCache.get(id, new Function<String, GoodsStock>() {
            @Override
            public GoodsStock apply(String s) {
                log.info("goodsStock数据库。。。。。。");
                return MyContext.stockMap.get(s);
            }
        });
        log.info("goodsStock。。。。。。");
        return goodsStock;
    }

}

package sp.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import sp.entity.Goods;
import sp.entity.GoodsStock;

import java.util.Map;

/**
 * @author lyq
 * @date 2022/2/21 2:39
 */
@Component
@Slf4j
public class RedisHandler implements InitializingBean {


    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 将数据库的热点数据提前放到redis
     */
    @Override
    public void afterPropertiesSet() throws Exception {

        log.info("mysql数据加载redis：开始。。。。");

        Map<String, Goods> goodsMap = MyContext.goodsMap;
        Map<String, GoodsStock> stockMap = MyContext.stockMap;


        for (Map.Entry<String, Goods> entries : goodsMap.entrySet()) {
            Goods goods = entries.getValue();
            String goodsJsonStr = MAPPER.writeValueAsString(goods);
            redisTemplate.opsForValue().set("goods:id:" + goods.getId(), goodsJsonStr);
        }

        for (Map.Entry<String, GoodsStock> entries : stockMap.entrySet()) {
            GoodsStock goodsStock = entries.getValue();
            String goodsStockJsonStr = MAPPER.writeValueAsString(goodsStock);
            redisTemplate.opsForValue().set("goods:stock:id:" + goodsStock.getGoodsId(), goodsStockJsonStr);
        }

        log.info("mysql数据加载redis：结束。。。。");
    }

    public void saveGoods(Goods goods) {
        String goodsJsonStr = null;
        try {
            goodsJsonStr = MAPPER.writeValueAsString(goods);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        redisTemplate.opsForValue().set("goods:id:" + goods.getId(), goodsJsonStr);
    }


    public void deleteGoods(Goods goods) {
        redisTemplate.delete("goods:id:" + goods.getId());
    }

}

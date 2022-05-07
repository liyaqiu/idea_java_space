package sp.canal;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sp.entity.Goods;
import sp.handler.RedisHandler;
import top.javatool.canal.client.annotation.CanalTable;
import top.javatool.canal.client.handler.EntryHandler;

/**
 * @author lyq
 * @date 2022/2/22 1:09
 */
@Component
@CanalTable("goods")
@Slf4j
public class GoodsCanalHandler implements EntryHandler<Goods> {


    @Autowired
    RedisHandler redisHandler;

    @Override
    public void insert(Goods goods) {
        log.info("insert..{}",goods);
        //redisHandler.saveGoods(goods);
    }

    @Override
    public void update(Goods before, Goods after) {
        log.info("update..{}",after);
        //redisHandler.saveGoods(goods);
    }

    @Override
    public void delete(Goods goods) {
        log.info("delete..{}",goods);
        //redisHandler.deleteGoods(goods);
    }
}

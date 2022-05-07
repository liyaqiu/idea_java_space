package sp.handler;

import sp.entity.Goods;
import sp.entity.GoodsStock;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lyq
 * @date 2022/2/20 23:43
 */
public class MyContext {
    public static Map<String, Goods> goodsMap = new HashMap<>();
    public static Map<String, GoodsStock> stockMap = new HashMap<>();

    static {
        Goods goods1 = new Goods("1", "iphone4", "7寸");
        Goods goods2 = new Goods("2", "iphone5", "14寸");

        GoodsStock goodsStock1 = new GoodsStock("111", 1000, "1");
        GoodsStock goodsStock2 = new GoodsStock("222", 7777, "2");

        goodsMap.put(goods1.getId(),goods1);
        goodsMap.put(goods2.getId(),goods2);

        stockMap.put(goodsStock1.getGoodsId(),goodsStock1);
        stockMap.put(goodsStock2.getGoodsId(),goodsStock2);
    }
}

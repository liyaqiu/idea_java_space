package sp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author lyq
 * @date 2022/2/20 22:39
 */
@Data
@AllArgsConstructor
public class GoodsStock {
    String id;
    int count;
    String goodsId;


}

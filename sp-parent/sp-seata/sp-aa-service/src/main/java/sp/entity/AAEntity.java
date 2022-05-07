package sp.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author lyq
 * @date 2022/1/22 6:44
 */
@Data
@TableName("aa")
@AllArgsConstructor
@NoArgsConstructor
public class AAEntity {
    String aaid;
    Integer aanumber;
}

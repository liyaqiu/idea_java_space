package sp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;

/**
 * @author lyq
 * @date 2022/2/20 22:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Goods {
    @Id
    String id;
    String name;
    String size;



}

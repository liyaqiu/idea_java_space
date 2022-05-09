package sp.web.entity;

import com.sun.tracing.dtrace.ArgsAttributes;
import lombok.*;

/**
 * @author eric
 * @date 2022/5/8 13:23
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserEntity {
    String name;
    String age;
}

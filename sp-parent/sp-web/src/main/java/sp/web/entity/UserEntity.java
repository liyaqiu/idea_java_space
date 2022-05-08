package sp.web.entity;

import com.sun.tracing.dtrace.ArgsAttributes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author eric
 * @date 2022/5/8 13:23
 **/
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    String name;
    String age;
}

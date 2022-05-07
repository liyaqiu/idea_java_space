package sp.tcc.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author lyq
 * @date 2022/1/26 10:42
 * DDL
 * CREATE TABLE `account_table` (
 *   `uid` varchar(255) NOT NULL,
 *   `money` int(10) unsigned DEFAULT NULL,
 *   PRIMARY KEY (`uid`)
 */
@Data
@TableName("account_table")
public class AccountEntity {
    String uid;
    Integer money;
}

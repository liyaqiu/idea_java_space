package sp.tcc.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author lyq
 * @date 2022/1/26 10:42
 * DDL
 * CREATE TABLE `account_tcc_table` (
 *   `xid` varchar(255) NOT NULL,
 *   `uid` varbinary(255) DEFAULT NULL,
 *   `dongjie_money` int(10) unsigned DEFAULT NULL,
 *   `state` int(11) DEFAULT NULL,
 *   PRIMARY KEY (`xid`)
 * ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
 */
@Data
@TableName("account_tcc_table")
public class AccountTccEntity {
    @TableId
    String xid;
    String uid;
    Integer dongjieMoney;
    Integer state;

    public static class State{
        public static Integer TRY = 0;
        public static Integer CONFIRM = 1;
        public static Integer CANCEL = 2;
    }

}

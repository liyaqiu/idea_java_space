package sp.tcc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import sp.entity.AAEntity;
import sp.tcc.entity.AccountEntity;

/**
 * @author lyq
 * @date 2022/1/22 6:51
 */
@Mapper
public interface AccountMapper extends BaseMapper<AccountEntity> {
    @Update("update account_table set money = money - ${money} where uid = #{uid}")
    int deduct(@Param("uid") String uid,@Param("money") int money);

    @Update("update account_table set money = money + ${money} where uid = #{uid}")
    int refund(@Param("uid") String uid,@Param("money") int money);

}

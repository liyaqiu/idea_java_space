package sp.tcc.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import sp.tcc.entity.AccountEntity;
import sp.tcc.entity.AccountTccEntity;

/**
 * @author lyq
 * @date 2022/1/22 6:51
 */
@Mapper
public interface AccountTccMapper extends BaseMapper<AccountTccEntity> {
}

package com.transaction;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lyq
 * @date 2021/12/5 12:32
 */
//@Mapper  @MapperScan("com.transaction")
//@Bean @ComponentScan()
@Mapper
public interface UserMapper1 extends BaseMapper<User> {
}

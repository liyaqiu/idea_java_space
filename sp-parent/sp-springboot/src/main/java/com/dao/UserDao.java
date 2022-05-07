package com.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author lyq
 * @date 2021/12/5 12:32
 */
@Mapper
public interface UserDao extends BaseMapper<User> {
}

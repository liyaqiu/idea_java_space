package com.mybatisplus;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mybatisplus.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author lyq
 * @date 2021/12/5 12:32
 */
@Mapper
public interface OrderMapper extends BaseMapper<Orders> {

    @Select("select * from orders where uid = #{uid}")
    public List<Orders> queryOrdersByUid(String uid);
}

package com.mybatisplus;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mybatisplus.entity.Orders;
import com.mybatisplus.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author lyq
 * @date 2021/12/5 12:32
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    @Select("SELECT r.*,ru.uid  from role r ,roleuser ru where ru.rid = r.id and uid = #{uid}")
    public List<Role> queryRolesByUid(String uid);
}

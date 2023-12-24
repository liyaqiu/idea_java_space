package com.mybatisplus;

import com.mybatisplus.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * @author lyq
 * @date 2021/12/5 12:32
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    public abstract User queryUserById(String id);


    @Select("select * from user where id = #{id}")
    @Results({
            @Result(id = true ,column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "age",property = "age")
    })
    public abstract User selectUserById(String id);

    @Select("select * from user where id = #{id}")
    public abstract User queryAutoPeopleById(String id);


    public abstract List<User> oneToMany();

    @Select("select * from user ")
    @Results({
            @Result(id = true ,column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "age",property = "age"),
            @Result(
                    column = "id", //根据用户的id字段去查询
                    property = "ordersList",
                    //一对一
                    //one = @One(select = "com.mybatisplus.OrderMapper.queryOrdersByUid")
                    //一对多
                    many = @Many(select = "com.mybatisplus.OrderMapper.queryOrdersByUid")
            )
    })
    public  List<User> oneToManyAnnotation();


    public  List<User> manyToMany();



    @Select("SELECT * from user ")
    @Results({
            @Result(id = true ,column = "id",property = "id"),
            @Result(column = "name",property = "name"),
            @Result(column = "age",property = "age"),
            @Result(
                    column = "id", //根据用户的rid字段去查询
                    property = "roleList",
                    //一对一
                    //one = @One(select = "com.mybatisplus.OrderMapper.queryOrdersByUid")
                    //多对多
                    many = @Many(select = "com.mybatisplus.RoleMapper.queryRolesByUid")
            )
    })
    public  List<User> manyToManyAnnotation();
}

package com.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * @author lyq
 * @date 2021/12/5 12:27
 */
@Getter
@Setter
@ToString
@TableName("user")
public class User {
    private String id;
    private String name;
    private Integer age;
    @TableField(exist = false)
    List<Orders> ordersList;
    @TableField(exist = false)
    List<Role> roleList;
}

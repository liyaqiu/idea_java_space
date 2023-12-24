package com.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author lyq
 * @date 2022/3/5 9:31
 */
@Getter
@Setter
@ToString
public class Role {
    private String id;
    private String name;
}

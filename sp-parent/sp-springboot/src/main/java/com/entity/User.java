package com.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * @author lyq
 * @date 2021/12/5 12:27
 */
@Data
@Component
@TableName("user")
public class User{

    @TableId
    private String id;
    private String name;
    private Integer age;
    private List<Integer> families;

}

package com.gzzn.service.edu.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author eric
 * @date 2022/9/2 22:54
 **/
@Data
@TableName("edu_teacher")
public class EduTeacherEntity {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String name;
    private String intro;
    private String career;
    private Integer level;
    private String avatar;
    private Integer sort;
    private String isDeleted;
    private Date gmtCreate;
    private Date gmtModified;
}

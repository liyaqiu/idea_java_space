package com.gzzn.service.edu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author eric
 * @date 2022/9/2 22:54
 **/
@Data
@TableName("edu_course_description")
public class EduCourseDescriptionEntity {
    @TableId(type = IdType.INPUT)
    private String id;
    private String description;
    private Date gmtCreate;
    private Date gmtModified;
}

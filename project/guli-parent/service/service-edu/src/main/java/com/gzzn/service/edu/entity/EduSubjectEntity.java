package com.gzzn.service.edu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author eric
 * @date 2022/9/2 22:54
 **/
@Data
@TableName("edu_subject")
public class EduSubjectEntity {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String title;
    private String parentId;
    private Integer sort;
    private Date gmtCreate;
    private Date gmtModified;
}

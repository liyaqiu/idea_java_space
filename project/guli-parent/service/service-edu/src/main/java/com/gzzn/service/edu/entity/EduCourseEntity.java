package com.gzzn.service.edu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author eric
 * @date 2022/9/2 22:54
 **/
@Data
@TableName("edu_course")
public class EduCourseEntity {
   @TableId(type = IdType.ASSIGN_ID)
   private String id;
   private String teacherId;
   private String subjectId;
   private String subjectParentId;
   private String title;
   private BigDecimal price;
   private Integer lessonNum;
   private String cover;
   private Long buyCount;
   private Long viewCount;
   private Long version;
   private String status;
   private String isDeleted;
   private Date gmtCreate;
   private Date gmtModified;


}

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
@TableName("edu_video")
public class EduVideoEntity {
   @TableId(type = IdType.ASSIGN_ID)
   private String id;
   private String courseId;
   private String chapterId;
   private String title;
   private String videoSourceId;
   private String videoOriginalName;
   private Integer sort;
   private Long playCount;
   private Short isFree;
   private Float duration;
   private String status;
   private Long size;
   private Long version;
   private Date gmtCreate;
   private Date gmtModified;
}

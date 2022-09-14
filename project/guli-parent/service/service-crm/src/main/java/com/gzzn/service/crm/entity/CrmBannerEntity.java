package com.gzzn.service.crm.entity;

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
@TableName("crm_banner")
public class CrmBannerEntity {
   @TableId(type = IdType.ASSIGN_ID)
   private String id;
   private String title;
   private String imageUrl;
   private String linkUrl;
   private Integer sort;
   private Short isDeleted;
   private Date gmtCreate;
   private Date gmtModified;
}

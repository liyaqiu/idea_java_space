package com.gzzn.service.acl.entity;

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
@TableName("acl_user")
public class AclUserEntity {
   @TableId(type = IdType.ASSIGN_ID)
   private String id;
   private String userName;
   private String passwd;
   private Integer sort;
   private Date gmtCreate;
   private Date gmtModified;

  /* id
           username
   password
           nick_name
   avatar
           is_deleted
   gmt_create
           gmt_modified*/

}

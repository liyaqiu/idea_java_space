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
   private String username;
   private String password;
   private String nickName;
   private String avatar;
   private Boolean locked;
   private Date gmtCreate;
   private Date gmtModified;
}

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
@TableName("acl_user_role")
public class AclUserRoleEntity {
   @TableId(type = IdType.ASSIGN_ID)
   private String id;
   private String roleId;
   private String userId;
   private Date gmtCreate;
   private Date gmtModified;
}

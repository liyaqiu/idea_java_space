package com.gzzn.service.acl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * @author eric
 * @date 2022/9/2 22:54
 **/
@Data
@TableName("acl_role_permission")
public class AclRolePermissionEntity {
   @TableId(type = IdType.ASSIGN_ID)
   private String id;
   private String roleId;
   private String permissionId;
   private Date gmtCreate;
   private Date gmtModified;



}

package com.gzzn.service.acl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author eric
 * @date 2022/10/5 13:49
 **/
@Data
@TableName("acl_permission")
public class AclPermissionEntity {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private Date gmtCreate;
    private Date gmtModified;
}

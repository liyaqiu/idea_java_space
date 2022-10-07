package com.gzzn.service.acl.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author eric
 * @date 2022/10/5 13:49
 **/
@Data
@TableName("acl_permission")
public class AclPermissionEntity {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String parentId;
    private String title;
    private String type;
    private String permit;
    private String component;
    private String icon;
    private String path;
    private String name;
    private Boolean hidden;
    private String status;
    private String isDeleted;
    private Date gmtCreate;
    private Date gmtModified;
    @TableField(exist = false)
    private List<AclPermissionEntity> children = new ArrayList<>();
}

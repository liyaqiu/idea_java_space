package com.sfkj.service.hospital.api.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * @author eric
 * @date 2022/11/22 19:02
 **/
@Data
@TableName("organization")
public class OrganizationEntity {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String name;
    private Date createTime;
    private Date updateTime;
}












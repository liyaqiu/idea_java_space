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
@TableName("conference_room")
public class ConferenceRoomEntity {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String name;
    private String number;
    private String imageUrl;
    private Integer capacity;
    private String phone;
    private Short status;
    private String deviceId;
    private String organizationId;
    private String departmentId;
    private String addressId;
    private Date createTime;
    private Date updateTime;
}












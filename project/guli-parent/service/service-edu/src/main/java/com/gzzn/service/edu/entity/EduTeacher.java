package com.gzzn.service.edu.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author eric
 * @date 2022/9/2 22:54
 **/
@Getter
@Setter
public class EduTeacher {
    @TableId(type = IdType.ASSIGN_ID)
    private String id;
    private String name;
    private String intro;
    private String career;
    private int level;
    private String avatar;
    private int sort;
    private String isDeleted;
    private Date gmtCreate;
    private Date gmtModified;
}

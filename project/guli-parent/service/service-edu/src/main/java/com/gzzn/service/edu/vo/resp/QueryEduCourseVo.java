package com.gzzn.service.edu.vo.resp;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author eric
 * @date 2022/9/9 16:58
 **/
@Data
public class QueryEduCourseVo {
    private String id;
    private String teacherId;
    private String subjectId;
    private String subjectParentId;
    private String title;
    private BigDecimal price;
    private Integer lessonNum;
    private String cover;
    private Long buyCount;
    private Long viewCount;
    private Long version;
    private String status;
    private String isDeleted;
    private Date gmtCreate;
    private Date gmtModified;
    private String description;
}

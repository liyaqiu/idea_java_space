package com.gzzn.service.edu.vo.resp;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author eric
 * @date 2022/9/9 16:58
 **/
@Data
public class ForntQueryEduCourseDetailVo {
    private String id;
    private String title;
    private String lessonNum;
    private BigDecimal price;
    private String cover;
    private Long buyCount;
    private Long viewCount;
    private String description;
    private String teacherName;
    private String intro;
    private String avatar;
    private String oneSubject;
    private String twoSubject;

}
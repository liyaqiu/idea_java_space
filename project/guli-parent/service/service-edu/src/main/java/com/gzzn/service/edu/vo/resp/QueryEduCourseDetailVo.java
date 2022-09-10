package com.gzzn.service.edu.vo.resp;

import com.gzzn.service.edu.entity.EduVideoEntity;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author eric
 * @date 2022/9/9 16:58
 **/
@Data
public class QueryEduCourseDetailVo {
    private String id;
    private String title;
    private String lessonNum;
    private BigDecimal price;
    private String cover;
    private String teacherName;
    private String oneSubject;
    private String twoSubject;
}

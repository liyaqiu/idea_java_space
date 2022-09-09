package com.gzzn.service.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gzzn.service.edu.entity.EduCourseDescriptionEntity;
import com.gzzn.service.edu.entity.EduCourseEntity;
import com.gzzn.service.edu.vo.resp.QueryEduCourseVo;

import java.util.Map;

/**
 * @author lyq
 * @date 2021/12/6 12:39
 */

public interface EduCourseService extends IService<EduCourseEntity> {

    Map<String, String> addEduCourse(EduCourseEntity eduCourse, EduCourseDescriptionEntity eduCourseDescription);

    QueryEduCourseVo queryEduCourse(String id);

    void updateEduCourse(EduCourseEntity eduCourse, EduCourseDescriptionEntity eduCourseDescription);
}

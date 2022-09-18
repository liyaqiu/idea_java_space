package com.gzzn.service.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gzzn.service.edu.entity.EduTeacherEntity;
import com.gzzn.service.edu.vo.resp.TeacherMetricVo;

import java.util.Date;
import java.util.List;

/**
 * @author lyq
 * @date 2021/12/6 12:39
 */

public interface EduTeacherService extends IService<EduTeacherEntity> {

    List<TeacherMetricVo> queryEduTeacherMetric(Date startDate, Date endDate);
}

package com.gzzn.service.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gzzn.service.edu.entity.EduTeacherEntity;
import com.gzzn.service.edu.mapper.EduTeacherMapper;
import com.gzzn.service.edu.service.EduTeacherService;
import com.gzzn.service.edu.vo.resp.TeacherMetricVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author lyq
 * @date 2021/12/6 12:43
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacherEntity> implements EduTeacherService {

    @Autowired
    EduTeacherMapper eduTeacherMapper;

    @Override
    public List<TeacherMetricVo> queryEduTeacherMetric(Date startDate, Date endDate) {
        /*QueryWrapper<TestEntity> wrapper = new QueryWrapper();
        if(startDate!=null){
            wrapper.ge("gmt_create", startDate);
        }
        if(endDate!=null){
            wrapper.le("gmt_create", endDate);
        }
        wrapper.select("career","count(0) as count");
        //SELECT career,count(0) as count from edu_teacher where gmt_create >= '2019-11-07' and gmt_create <= '2020-08-04' GROUP BY career
        wrapper.groupBy("career");*/

        List<TeacherMetricVo> list = eduTeacherMapper.selectMetricList(startDate, endDate);
        return list;
    }
}


package com.gzzn.service.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gzzn.service.edu.converter.EduCourseConverter;
import com.gzzn.service.edu.entity.EduCourseDescriptionEntity;
import com.gzzn.service.edu.entity.EduCourseEntity;
import com.gzzn.service.edu.mapper.EduCourseDescriptionMapper;
import com.gzzn.service.edu.mapper.EduCourseMapper;
import com.gzzn.service.edu.service.EduCourseService;
import com.gzzn.service.edu.vo.resp.QueryEduCourseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lyq
 * @date 2021/12/6 12:43
 */
@Service
@Slf4j
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourseEntity> implements EduCourseService {

    @Autowired
    EduCourseMapper eduCourseMapper;
    @Autowired
    EduCourseDescriptionMapper eduCourseDescriptionMapper;


    @Override
    @Transactional
    public Map<String, String> addEduCourse(EduCourseEntity eduCourse, EduCourseDescriptionEntity eduCourseDescription) {
        if(eduCourseMapper.insert(eduCourse)!=1){
            throw new RuntimeException("添加课程失败");
        }
        eduCourseDescription.setId(eduCourse.getId());
        if(eduCourseDescriptionMapper.insert(eduCourseDescription)!=1){
            throw new RuntimeException("添加课程描述失败");
        }
        Map<String,String> map = new HashMap<>();
        map.put("id", eduCourse.getId());
        return map;
    }

    @Override
    public QueryEduCourseVo queryEduCourse(String id) {
        EduCourseEntity eduCourse = eduCourseMapper.selectById(id);
        QueryEduCourseVo vo = EduCourseConverter.INSTANCE.convert(eduCourse);

        EduCourseDescriptionEntity eduCourseDescription = eduCourseDescriptionMapper.selectById(id);
        if(eduCourseDescription!=null){
            vo.setDescription(eduCourseDescription.getDescription());
        }
        return vo;
    }

    @Override
    public void updateEduCourse(EduCourseEntity eduCourse, EduCourseDescriptionEntity eduCourseDescription) {

        if (eduCourseMapper.selectById(eduCourse.getId()) == null) {
            throw new RuntimeException("课程不存在无法修改");
        }

        if (eduCourseMapper.updateById(eduCourse)!=1) {
            throw new RuntimeException("课程修改失败");
        }

        eduCourseDescription.setId(eduCourse.getId());
        if (eduCourseDescriptionMapper.updateById(eduCourseDescription)!=1) {
            throw new RuntimeException("课程描述修改失败");
        }
    }
}

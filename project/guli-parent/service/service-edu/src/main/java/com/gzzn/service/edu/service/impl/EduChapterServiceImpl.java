package com.gzzn.service.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gzzn.service.edu.entity.EduChapterEntity;
import com.gzzn.service.edu.mapper.EduChapterMapper;
import com.gzzn.service.edu.mapper.EduCourseMapper;
import com.gzzn.service.edu.service.EduChapterService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lyq
 * @date 2021/12/6 12:43
 */
@Service
@Slf4j
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapterEntity> implements EduChapterService {

    @Autowired
    EduChapterMapper eduChapterMapper;
    @Autowired
    EduCourseMapper eduCourseMapper;

    @Override
    @Transactional
    public void addChapter(EduChapterEntity eduChapter) {
        if (eduCourseMapper.selectById(eduChapter.getCourseId())==null) {
            throw new RuntimeException("课程不存在无法添加");
        }
        if (eduChapterMapper.insert(eduChapter)!=1) {
            throw new RuntimeException("添加章节失败");
        }
    }
}

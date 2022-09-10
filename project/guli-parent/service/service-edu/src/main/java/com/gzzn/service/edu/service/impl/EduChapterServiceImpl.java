package com.gzzn.service.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gzzn.service.edu.converter.EduChapterConverter;
import com.gzzn.service.edu.entity.EduChapterEntity;
import com.gzzn.service.edu.mapper.EduChapterMapper;
import com.gzzn.service.edu.mapper.EduCourseMapper;
import com.gzzn.service.edu.mapper.EduVideoMapper;
import com.gzzn.service.edu.service.EduChapterService;
import com.gzzn.service.edu.vo.resp.QueryChapterVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    @Autowired
    EduVideoMapper eduVideoMapper;

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

    @Override
    public List<QueryChapterVo> queryChapter(String courseId) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("course_id", courseId);
        List<EduChapterEntity> eduChapterList = eduChapterMapper.selectList(wrapper);
        List<QueryChapterVo> chapterVoList = EduChapterConverter.INSTANCE.convert(eduChapterList);
        for (QueryChapterVo chapterVo : chapterVoList) {
            QueryWrapper videoWqpper = new QueryWrapper();
            videoWqpper.eq("chapter_id", chapterVo.getId());
            chapterVo.setEduVideoList(eduVideoMapper.selectList(videoWqpper));
        }
        return chapterVoList;
    }

    @Override
    @Transactional
    public void removeChapter(String id) {
        if (eduChapterMapper.selectById(id)==null) {
            throw new RuntimeException("章节不存在，无法删除");
        }
        QueryWrapper videoWqpper = new QueryWrapper();
        videoWqpper.eq("chapter_id", id);
        if(eduVideoMapper.selectList(videoWqpper).size()!=0){
            throw new RuntimeException("请先删除小节，在删除章节");
        }
        if (eduChapterMapper.deleteById(id)!=1) {
            throw new RuntimeException("删除章节失败");
        }
    }

    @Override
    public void updateChapter(EduChapterEntity eduChapter) {
        if (eduChapterMapper.updateById(eduChapter)!=1) {
            throw new RuntimeException("修改章节失败");
        }
    }
}

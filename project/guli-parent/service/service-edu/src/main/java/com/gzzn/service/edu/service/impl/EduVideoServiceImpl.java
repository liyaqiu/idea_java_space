package com.gzzn.service.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gzzn.service.edu.entity.EduVideoEntity;
import com.gzzn.service.edu.mapper.EduChapterMapper;
import com.gzzn.service.edu.mapper.EduVideoMapper;
import com.gzzn.service.edu.service.EduVideoService;
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
public class EduVideoServiceImpl extends ServiceImpl<EduVideoMapper, EduVideoEntity> implements EduVideoService {

    @Autowired
    EduVideoMapper eduVideoMapper;
    @Autowired
    EduChapterMapper eduChapterMapper;

    @Override
    @Transactional
    public void addVideo(EduVideoEntity eduVideo) {
        if (eduChapterMapper.selectById(eduVideo.getChapterId())==null) {
            throw new RuntimeException("改章节不存在，不允许添加小节");
        }
        if (eduVideoMapper.insert(eduVideo)!=1) {
            throw new RuntimeException("添加小节失败");
        }
    }

    @Override
    @Transactional
    public void updateVideo(EduVideoEntity eduVideo) {
        if (eduVideoMapper.updateById(eduVideo)!=1) {
            throw new RuntimeException("修改小节失败");
        }
    }

    @Override
    @Transactional
    public void removeVideo(String id) {
        if (eduVideoMapper.deleteById(id)!=1) {
            throw new RuntimeException("删除小节失败");
        }
    }
}

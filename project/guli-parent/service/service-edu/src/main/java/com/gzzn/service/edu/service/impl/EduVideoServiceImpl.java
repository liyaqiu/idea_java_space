package com.gzzn.service.edu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gzzn.service.edu.client.ServiceOssClient;
import com.gzzn.service.edu.entity.EduVideoEntity;
import com.gzzn.service.edu.mapper.EduChapterMapper;
import com.gzzn.service.edu.mapper.EduVideoMapper;
import com.gzzn.service.edu.service.EduVideoService;
import com.gzzn.service.utils.Res;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

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
    @Autowired
    ServiceOssClient serviceOssClient;


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
        EduVideoEntity eduVideo = eduVideoMapper.selectById(id);
        if(eduVideo==null){
            throw new RuntimeException("小节不存在，不允许删除");
        }
        if (eduVideoMapper.deleteById(id)!=1) {
            throw new RuntimeException("删除小节失败");
        }
        if(!StringUtils.isEmpty(eduVideo.getVideoOriginalName())){
            Res res = serviceOssClient.removeVideo(eduVideo.getVideoOriginalName());
            if(res == null){
                throw new RuntimeException("oss服务连接失败");
            }
            if(!res.isSuccess()){
                throw new RuntimeException("删除视频资源失败");
            }
        }
    }
}

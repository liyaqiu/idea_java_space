package com.gzzn.service.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gzzn.service.edu.entity.EduVideoEntity;

/**
 * @author lyq
 * @date 2021/12/6 12:39
 */

public interface EduVideoService extends IService<EduVideoEntity> {
    void addVideo(EduVideoEntity eduVideo);

    void updateVideo(EduVideoEntity eduVideo);

    void removeVideo(String id);
}

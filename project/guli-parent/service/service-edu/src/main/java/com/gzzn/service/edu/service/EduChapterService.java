package com.gzzn.service.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gzzn.service.edu.entity.EduChapterEntity;

/**
 * @author lyq
 * @date 2021/12/6 12:39
 */

public interface EduChapterService extends IService<EduChapterEntity> {
    void addChapter(EduChapterEntity eduChapter);
}

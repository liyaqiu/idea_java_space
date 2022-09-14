package com.gzzn.service.acl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gzzn.service.edu.entity.EduChapterEntity;
import com.gzzn.service.edu.vo.resp.QueryChapterVo;

import java.util.List;

/**
 * @author lyq
 * @date 2021/12/6 12:39
 */

public interface EduChapterService extends IService<EduChapterEntity> {
    void addChapter(EduChapterEntity eduChapter);

    List<QueryChapterVo> queryChapter(String courseId);

    void removeChapter(String id);

    void updateChapter(EduChapterEntity eduChapter);
}

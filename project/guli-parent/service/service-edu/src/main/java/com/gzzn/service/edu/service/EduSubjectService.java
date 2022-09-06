package com.gzzn.service.edu.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gzzn.service.edu.entity.EduSubjectEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author lyq
 * @date 2021/12/6 12:39
 */

public interface EduSubjectService extends IService<EduSubjectEntity> {

    void addEduSubject(MultipartFile file);
}

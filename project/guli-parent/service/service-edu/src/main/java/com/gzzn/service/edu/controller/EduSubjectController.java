package com.gzzn.service.edu.controller;

import com.gzzn.test.service.common.utils.Res;
import com.gzzn.service.edu.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author lyq
 * @date 2021/12/4 12:33
 */
@RestController
@RequestMapping("/edu/subject")
@Slf4j
@Api(tags = {"课程分类管理"})
public class EduSubjectController {

    @Autowired
    private EduTeacherService  eduTeacherService;

    @PostMapping
    @ApiOperation("添加课程分类")
    public Res addEduSubject(MultipartFile file){
        log.debug("addEduSubject {}",file);
        return Res.ok();
    }

}

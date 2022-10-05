package com.gzzn.service.edu.controller.frontend;

import com.gzzn.service.edu.entity.EduSubjectEntity;
import com.gzzn.service.edu.service.EduSubjectService;
import com.gzzn.service.utils.Res;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lyq
 * @date 2021/12/4 12:33
 */
@RestController
@RequestMapping("/edu/frontend/subject")
@Slf4j
@Api(tags = {"前端课程分类管理"})
public class EduFrontendSubjectController {

    @Autowired
    private EduSubjectService eduSubjectService;

    @GetMapping("/all")
    @ApiOperation("课程分类列表")
    public Res queryAllEduSubject(){
        log.debug("queryAllEduSubject");
        List<EduSubjectEntity> eduSubjectList = eduSubjectService.queryAllEduSubject();
        return Res.ok().setData(eduSubjectList);
    }

}

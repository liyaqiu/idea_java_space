package com.gzzn.service.edu.controller;

import com.gzzn.service.edu.converter.EduCourseConverter;
import com.gzzn.service.edu.converter.EduCourseDescriptionConverter;
import com.gzzn.service.edu.converter.EduTeacherConverter;
import com.gzzn.service.edu.entity.EduCourseDescriptionEntity;
import com.gzzn.service.edu.entity.EduCourseEntity;
import com.gzzn.service.edu.entity.EduTeacherEntity;
import com.gzzn.service.edu.service.EduCourseService;
import com.gzzn.service.edu.vo.req.AddEduCourseVo;
import com.gzzn.service.common.utils.Res;
import com.gzzn.service.edu.vo.req.UpdateEduCourseVo;
import com.gzzn.service.edu.vo.req.UpdateEduTeacherVo;
import com.gzzn.service.edu.vo.resp.QueryEduCourseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Map;

/**
 * @author lyq
 * @date 2021/12/4 12:33
 */
@RestController
@RequestMapping("/edu/course")
@Slf4j
@Api(tags = {"课程管理"})
public class EduCourseController {

    @Autowired
    private EduCourseService eduCourseService;

    @PostMapping
    @ApiOperation("添加课程")
    public Res addEduCourse(@RequestBody AddEduCourseVo vo){
        log.debug("addEduCourse {}",vo);

        EduCourseEntity eduCourse = EduCourseConverter.INSTANCE.convert(vo);
        eduCourse.setGmtCreate(new Date());
        eduCourse.setGmtModified(new Date());

        EduCourseDescriptionEntity eduCourseDescription = EduCourseDescriptionConverter.INSTANCE.convert(vo);
        eduCourseDescription.setGmtCreate(new Date());
        eduCourseDescription.setGmtModified(new Date());

        Map<String,String> map =  eduCourseService.addEduCourse(eduCourse,eduCourseDescription);
        return Res.ok().setData(map);
    }

    @PutMapping
    @ApiOperation("修改课程")
    public Res updateEduCourse(@RequestBody @Validated UpdateEduCourseVo vo){
        log.debug("updateEduTeacher {}",vo);

        EduCourseEntity eduCourse = EduCourseConverter.INSTANCE.convert(vo);
        eduCourse.setGmtModified(new Date());

        EduCourseDescriptionEntity eduCourseDescription = EduCourseDescriptionConverter.INSTANCE.convert(vo);
        eduCourseDescription.setGmtModified(new Date());

        eduCourseService.updateEduCourse(eduCourse,eduCourseDescription);
        return Res.ok();
    }

    @GetMapping("/{id}")
    @ApiOperation("查询课程")
    public Res queryEduCourse(@PathVariable("id") String id){
        log.debug("queryEduCourse {}",id);
        QueryEduCourseVo vo =  eduCourseService.queryEduCourse(id);
        return Res.ok().setData(vo);
    }

}

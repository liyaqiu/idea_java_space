package com.gzzn.service.edu.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzzn.service.edu.converter.EduCourseConverter;
import com.gzzn.service.edu.converter.EduCourseDescriptionConverter;
import com.gzzn.service.edu.converter.EduTeacherConverter;
import com.gzzn.service.edu.entity.EduCourseDescriptionEntity;
import com.gzzn.service.edu.entity.EduCourseEntity;
import com.gzzn.service.edu.entity.EduTeacherEntity;
import com.gzzn.service.edu.service.EduCourseService;
import com.gzzn.service.edu.vo.req.*;
import com.gzzn.service.common.utils.Res;
import com.gzzn.service.edu.vo.resp.QueryEduCourseDetailVo;
import com.gzzn.service.edu.vo.resp.QueryEduCourseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
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

    @GetMapping("/detail/{id}")
    @ApiOperation("查询课程详细信息")
    public Res queryEduCourseDetail(@PathVariable("id") String id){
        log.debug("queryEduCourseDetail {}",id);
        QueryEduCourseDetailVo vo =  eduCourseService.queryEduCourseDetail(id);
        return Res.ok().setData(vo);
    }

    @PutMapping("/publish/{id}")
    @ApiOperation("发布课程")
    public Res publishEduCourse(@PathVariable("id") String id){
        log.debug("publishEduCourse {}",id);
        EduCourseEntity eduCourse = new EduCourseEntity();
        eduCourse.setId(id);
        eduCourse.setStatus(EduCourseEntity.NORMAL);
        eduCourseService.publishEduCourse(eduCourse);
        return Res.ok();
    }

    @GetMapping("/{currentPage}/{pageSize}")
    @ApiOperation("条件分页查询课程")
    public Res PageQueryEduCourse(@ApiParam(name = "currentPage",value = "当前页",required = true) @PathVariable("currentPage") long currentPage,
                                  @ApiParam(name = "pageSize",value = "每页大小",required = true) @PathVariable("pageSize") long pageSize,
                                  @Validated PageQueryEduCourseVo vo){
        log.debug("PageQueryEduCourse {} {} {}",currentPage,pageSize,vo);

        QueryWrapper wrapper = new QueryWrapper();
        if(vo.getBeginTime()!=null){
            wrapper.ge("gmt_create", vo.getBeginTime());
        }
        if(vo.getEndTime()!=null){
            wrapper.le("gmt_create", vo.getEndTime());
        }
        if(!StringUtils.isEmpty(vo.getTitle())){
            wrapper.like("title", vo.getTitle());
        }
        if(!StringUtils.isEmpty(vo.getStatus())){
            wrapper.eq("status", vo.getStatus());
        }

        wrapper.orderByDesc("gmt_create");

        IPage<EduCourseEntity> page = new Page<>(currentPage,pageSize);
        eduCourseService.page(page, wrapper);
        Map<String,Object> map = new HashMap<>();
        map.put("total", page.getTotal());
        map.put("records", page.getRecords());
        return Res.ok().setData(map);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除课程")
    public Res removeEduCourse(@PathVariable("id") String id){
        log.debug("removeEduCourse {}",id);
        eduCourseService.removeEduCourse(id);
        return Res.ok();
    }

}

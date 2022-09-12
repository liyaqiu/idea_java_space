package com.gzzn.service.edu.controller;

import com.gzzn.service.edu.converter.EduChapterConverter;
import com.gzzn.service.edu.converter.EduCourseConverter;
import com.gzzn.service.edu.converter.EduCourseDescriptionConverter;
import com.gzzn.service.edu.entity.EduChapterEntity;
import com.gzzn.service.edu.entity.EduCourseDescriptionEntity;
import com.gzzn.service.edu.entity.EduCourseEntity;
import com.gzzn.service.edu.service.EduChapterService;
import com.gzzn.service.edu.vo.req.AddEduChapterVo;
import com.gzzn.service.common.utils.Res;
import com.gzzn.service.edu.vo.req.UpdateEduChapterVo;
import com.gzzn.service.edu.vo.req.UpdateEduCourseVo;
import com.gzzn.service.edu.vo.resp.QueryChapterVo;
import com.gzzn.service.edu.vo.resp.QueryEduCourseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @author lyq
 * @date 2021/12/4 12:33
 */
@RestController
@RequestMapping("/edu/chapter")
@Slf4j
@Api(tags = {"章节管理"})
public class EduChapterController {

    @Autowired
    private EduChapterService eduChapterService;

    @PostMapping
    @ApiOperation("添加章节")
    public Res addChapter(@RequestBody AddEduChapterVo vo){
        log.debug("addChapter {}",vo);
        EduChapterEntity eduChapter = EduChapterConverter.INSTANCE.convert(vo);
        eduChapter.setGmtCreate(new Date());
        eduChapter.setGmtModified(new Date());
        eduChapterService.addChapter(eduChapter);
        return Res.ok();
    }


    @GetMapping("/{courseId}")
    @ApiOperation("查询章节")
    public Res queryChapter(@PathVariable("courseId") String courseId){
        log.debug("queryChapter {}",courseId);
        List<QueryChapterVo> list = eduChapterService.queryChapter(courseId);
        return Res.ok().setData(list);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除章节")
    public Res removeChapter(@PathVariable("id") String id){
        log.debug("removeChapter {}",id);
        eduChapterService.removeChapter(id);
        return Res.ok();
    }

    @PutMapping
    @ApiOperation("修改章节")
    public Res updateChapter(@RequestBody @Validated UpdateEduChapterVo vo){
        log.debug("updateChapter {}",vo);

        EduChapterEntity eduChapter = EduChapterConverter.INSTANCE.convert(vo);
        eduChapter.setGmtModified(new Date());

        eduChapterService.updateChapter(eduChapter);
        return Res.ok();
    }

    //http://localhost:8001/edu/chapter?ids=1,2,3
    //http://localhost:8001/edu/chapter?ids=1&ids=2&ids=3
    @DeleteMapping()
    @ApiOperation("111111")
    public Res test(String[] ids){
        System.out.println(ids);
        //log.debug("removeChapter {} {} {}", ids[0],ids[1],ids[2]);
        return Res.ok();
    }


}

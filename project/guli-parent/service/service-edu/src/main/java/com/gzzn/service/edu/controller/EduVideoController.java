package com.gzzn.service.edu.controller;

import com.gzzn.service.common.utils.Res;
import com.gzzn.service.edu.converter.EduChapterConverter;
import com.gzzn.service.edu.converter.EduCourseConverter;
import com.gzzn.service.edu.converter.EduCourseDescriptionConverter;
import com.gzzn.service.edu.converter.EduVideoConverter;
import com.gzzn.service.edu.entity.EduChapterEntity;
import com.gzzn.service.edu.entity.EduCourseDescriptionEntity;
import com.gzzn.service.edu.entity.EduCourseEntity;
import com.gzzn.service.edu.entity.EduVideoEntity;
import com.gzzn.service.edu.service.EduVideoService;
import com.gzzn.service.edu.vo.req.AddEduChapterVo;
import com.gzzn.service.edu.vo.req.AddEduVideoVo;
import com.gzzn.service.edu.vo.req.UpdateEduCourseVo;
import com.gzzn.service.edu.vo.req.UpdateEduVideoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * @author lyq
 * @date 2021/12/4 12:33
 */
@RestController
@RequestMapping("/edu/video")
@Slf4j
@Api(tags = {"小节管理"})
public class EduVideoController {

    @Autowired
    private EduVideoService eduVideoService;

    @PostMapping
    @ApiOperation("添加小节")
    public Res addVideo(@RequestBody @Validated AddEduVideoVo vo){
        log.debug("addVideo {}",vo);
        EduVideoEntity eduVideo = EduVideoConverter.INSTANCE.convert(vo);
        eduVideo.setGmtCreate(new Date());
        eduVideo.setGmtModified(new Date());
        eduVideoService.addVideo(eduVideo);
        return Res.ok();
    }

    @PutMapping
    @ApiOperation("修改小节")
    public Res updateVideo(@RequestBody @Validated UpdateEduVideoVo vo){
        log.debug("updateVideo {}",vo);
        EduVideoEntity eduVideo = EduVideoConverter.INSTANCE.convert(vo);
        eduVideo.setGmtModified(new Date());
        eduVideoService.updateVideo(eduVideo);
        return Res.ok();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除小节")
    public Res removeVideo(@PathVariable("id") String id){
        log.debug("removeVideo {}",id);
        eduVideoService.removeVideo(id);
        return Res.ok();
    }

}

package com.gzzn.service.edu.controller;

import com.gzzn.service.edu.converter.EduChapterConverter;
import com.gzzn.service.edu.entity.EduChapterEntity;
import com.gzzn.service.edu.service.EduChapterService;
import com.gzzn.service.edu.vo.req.AddEduChapterVo;
import com.gzzn.service.common.utils.Res;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

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



}

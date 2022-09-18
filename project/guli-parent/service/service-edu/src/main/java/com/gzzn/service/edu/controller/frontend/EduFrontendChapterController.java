package com.gzzn.service.edu.controller.frontend;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gzzn.service.common.utils.Res;
import com.gzzn.service.edu.converter.EduChapterConverter;
import com.gzzn.service.edu.entity.EduChapterEntity;
import com.gzzn.service.edu.service.EduChapterService;
import com.gzzn.service.edu.vo.req.AddEduChapterVo;
import com.gzzn.service.edu.vo.req.UpdateEduChapterVo;
import com.gzzn.service.edu.vo.resp.QueryChapterVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author lyq
 * @date 2021/12/4 12:33
 */
@RestController
@RequestMapping("/edu/frontend/chapter")
@Slf4j
@Api(tags = {"前端章节管理"})
public class EduFrontendChapterController {

    @Autowired
    private EduChapterService eduChapterService;


    @GetMapping("/{courseId}")
    @ApiOperation("查询章节")
    public Res queryChapter(@PathVariable("courseId") String courseId){
        log.debug("queryChapter {}",courseId);
        List<QueryChapterVo> list = eduChapterService.queryChapter(courseId);
        return Res.ok().setData(list);
    }
}

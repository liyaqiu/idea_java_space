package com.gzzn.service.edu.controller.frontend;

import com.gzzn.service.edu.service.EduChapterService;
import com.gzzn.service.edu.vo.resp.QueryChapterVo;
import com.gzzn.service.utils.Res;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

package com.gzzn.service.edu.controller.frontend;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gzzn.service.common.utils.Res;
import com.gzzn.service.edu.entity.EduTeacherEntity;
import com.gzzn.service.edu.service.EduTeacherService;
import com.gzzn.service.edu.vo.resp.TeacherMetricVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @author lyq
 * @date 2021/12/4 12:33
 */
@RestController
@RequestMapping("/edu/frontend/teacher")
@Slf4j
@Api(tags = {"前端讲师管理"})
public class EduFrontendTeacherController {

    @Autowired
    private EduTeacherService  eduTeacherService;

    @GetMapping
    @ApiOperation("查询讲师")
    public Res queryEduTeacher(){
        log.debug("queryEduTeacher");
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.orderByDesc("gmt_create");
        wrapper.last("limit 8");
        List list = eduTeacherService.list(wrapper);
        return Res.ok().setData(list);
    }

    @GetMapping("/{id}")
    @ApiOperation("根据Id查询讲师")
    public Res queryEduTeacherById(@PathVariable("id") String id){
        log.debug("queryEduTeacherById");
        EduTeacherEntity eduTeacher = eduTeacherService.getById(id);
        return Res.ok().setData(eduTeacher);
    }

    @GetMapping("/metric")
    @ApiOperation("查询讲师指标")
    public Res queryEduTeacherMetric(@DateTimeFormat(pattern = "yyyy-MM-dd")Date startDate, @DateTimeFormat(pattern = "yyyy-MM-dd")Date endDate){
        log.debug("queryEduTeacherMetric startDate {} endDate {}",startDate,endDate);
        List<TeacherMetricVo> list = eduTeacherService.queryEduTeacherMetric(startDate, endDate);
        return Res.ok().setData(list);
    }
}

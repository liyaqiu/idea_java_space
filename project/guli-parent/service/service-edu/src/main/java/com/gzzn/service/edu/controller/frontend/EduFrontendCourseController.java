package com.gzzn.service.edu.controller.frontend;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzzn.service.common.utils.Res;
import com.gzzn.service.edu.converter.EduCourseConverter;
import com.gzzn.service.edu.converter.EduCourseDescriptionConverter;
import com.gzzn.service.edu.entity.EduCourseDescriptionEntity;
import com.gzzn.service.edu.entity.EduCourseEntity;
import com.gzzn.service.edu.service.EduCourseService;
import com.gzzn.service.edu.vo.req.AddEduCourseVo;
import com.gzzn.service.edu.vo.req.FrontendPageQueryEduCourseVo;
import com.gzzn.service.edu.vo.req.PageQueryEduCourseVo;
import com.gzzn.service.edu.vo.req.UpdateEduCourseVo;
import com.gzzn.service.edu.vo.resp.ForntQueryEduCourseDetailVo;
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
@RequestMapping("/edu/frontend/course")
@Slf4j
@Api(tags = {"前端课程管理"})
public class EduFrontendCourseController {

    @Autowired
    private EduCourseService eduCourseService;

    @GetMapping("/{currentPage}/{pageSize}")
    @ApiOperation("条件分页查询课程")
    public Res PageQueryEduCourse(@ApiParam(name = "currentPage",value = "当前页",required = true) @PathVariable("currentPage") long currentPage,
                                  @ApiParam(name = "pageSize",value = "每页大小",required = true) @PathVariable("pageSize") long pageSize,
                                  @Validated FrontendPageQueryEduCourseVo vo){
        log.debug("PageQueryEduCourse {} {} {}",currentPage,pageSize,vo);

        QueryWrapper wrapper = new QueryWrapper();

        if(!StringUtils.isEmpty(vo.getSubjectParentId())){
            wrapper.eq("subject_parent_id", vo.getSubjectParentId());
        }
        if(!StringUtils.isEmpty(vo.getSubjectId())){
            wrapper.eq("subject_id", vo.getSubjectId());
        }


        if(!StringUtils.isEmpty(vo.getPriceSort())){
            if("asc".equals(vo.getPriceSort())){
                wrapper.orderByAsc("price");
            }else{
                wrapper.orderByDesc("price");
            }
        }

        if(!StringUtils.isEmpty(vo.getSaleSort())){
            if("asc".equals(vo.getPriceSort())){
                wrapper.orderByAsc("buy_count");
            }else{
                wrapper.orderByDesc("buy_count");
            }
        }

        IPage<EduCourseEntity> page = new Page<>(currentPage,pageSize);
        eduCourseService.page(page, wrapper);
        Map<String,Object> map = new HashMap<>();
        map.put("total", page.getTotal());
        map.put("records", page.getRecords());
        return Res.ok().setData(map);
    }

    @GetMapping("/{id}")
    @ApiOperation("查询课程详情")
    public Res queryEduCourseDetail(@PathVariable("id") String id){
        log.debug("queryEduCourseDetail {}",id);
        ForntQueryEduCourseDetailVo vo = eduCourseService.forntQueryEduCourseDetail(id);
        return Res.ok().setData(vo);
    }
}

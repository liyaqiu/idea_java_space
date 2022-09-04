package com.gzzn.service.edu.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gzzn.service.common.utils.Res;
import com.gzzn.service.edu.converter.EduTeacherConverter;
import com.gzzn.service.edu.dto.AddEduTeacherDto;
import com.gzzn.service.edu.dto.PageQueryEduTeacherDto;
import com.gzzn.service.edu.dto.UpdateEduTeacherDto;
import com.gzzn.service.edu.entity.EduTeacherEntity;
import com.gzzn.service.edu.service.EduTeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@RequestMapping("/edu/teacher")
@Slf4j
@Api(tags = {"讲师管理"})
public class EduTeacherController {

    @Autowired
    private EduTeacherService  eduTeacherService;

    @PostMapping
    @ApiOperation("添加讲师")
    public Res addEduTeacher(@RequestBody @Validated AddEduTeacherDto dto){
        log.debug("addEduTeacher {}",dto);
        EduTeacherEntity eduTeacher = EduTeacherConverter.INSTANCE.convert(dto);
        eduTeacher.setGmtCreate(new Date());
        eduTeacher.setGmtModified(new Date());
        System.out.println(JSONObject.toJSON(eduTeacher));
        eduTeacherService.save(eduTeacher);
        return Res.ok();
    }

    @DeleteMapping("/{id}")
    @ApiOperation("删除讲师")
    public Res removeEduTeacher(@PathVariable("id") String id){
        log.debug("removeEduTeacher {}",id);
        if(!eduTeacherService.removeById(id)){
            throw new RuntimeException("删除失败");
        }
        return Res.ok();
    }

    @PutMapping
    @ApiOperation("修改讲师")
    public Res updateEduTeacher(@RequestBody @Validated UpdateEduTeacherDto dto){
        log.debug("updateEduTeacher {}",dto);
        EduTeacherEntity eduTeacher = EduTeacherConverter.INSTANCE.convert(dto);
        eduTeacher.setGmtModified(new Date());
        if(!eduTeacherService.updateById(eduTeacher)){
            throw new RuntimeException("修改失败");
        }
        return Res.ok();
    }



    @GetMapping("/{currentPage}/{pageSize}")
    @ApiOperation("条件分页查询讲师")
    public Res PageQueryEduTeacher(@PathVariable("currentPage") long currentPage,
                                   @PathVariable("pageSize") long pageSize,
                                   PageQueryEduTeacherDto dto){
        log.debug("PageQueryEduTeacher {} {} {}",currentPage,pageSize,dto);

        QueryWrapper wrapper = new QueryWrapper();
        if(dto.getBeginTime()!=null){
            wrapper.ge("gmt_create", dto.getBeginTime());
        }
        if(dto.getEndTime()!=null){
            wrapper.le("gmt_create", dto.getEndTime());
        }
        if(!StringUtils.isEmpty(dto.getName())){
            wrapper.like("name", dto.getEndTime());
        }
        if(!StringUtils.isEmpty(dto.getLevel())){
            wrapper.eq("level", dto.getEndTime());
        }

        wrapper.orderByDesc("gmt_create");

        IPage<EduTeacherEntity> page = new Page<>(currentPage,pageSize);
        eduTeacherService.page(page, wrapper);
        Map<String,Object> map = new HashMap<>();
        map.put("total", page.getTotal());
        map.put("records", page.getRecords());
        return Res.ok().setData(map);
    }

    public static void main(String[] args) {
        System.out.println(new Date());
    }

}

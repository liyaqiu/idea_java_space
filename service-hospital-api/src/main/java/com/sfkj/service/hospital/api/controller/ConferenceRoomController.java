package com.sfkj.service.hospital.api.controller;


import com.sfkj.service.hospital.api.entity.AddressEntity;
import com.sfkj.service.hospital.api.utils.Res;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lyq
 * @date 2021/12/4 12:33
 */
@RestController
@RequestMapping("/hospital/conference-room")
@Slf4j
@Api(tags = {"会议室管理"})
@CrossOrigin
public class ConferenceRoomController {

    /*@Autowired
    private EduTeacherService  eduTeacherService;*/

    /*@PostMapping
    @ApiOperation("添加讲师")
    public Res addEduTeacher(@RequestBody @Validated AddEduTeacherVo vo){
        log.debug("addEduTeacher {}",vo);
        EduTeacherEntity eduTeacher = EduTeacherConverter.INSTANCE.convert(vo);
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
    public Res updateEduTeacher(@RequestBody @Validated UpdateEduTeacherVo vo){
        log.debug("updateEduTeacher {}",vo);
        EduTeacherEntity eduTeacher = EduTeacherConverter.INSTANCE.convert(vo);
        eduTeacher.setGmtModified(new Date());
        if(!eduTeacherService.updateById(eduTeacher)){
            throw new RuntimeException("修改失败");
        }
        return Res.ok();
    }


    @ApiImplicitParam
    @GetMapping("/{currentPage}/{pageSize}")
    @ApiOperation("条件分页查询讲师")
    public Res PageQueryEduTeacher(@ApiParam(name = "currentPage",value = "当前页",required = true) @PathVariable("currentPage") long currentPage,
                                   @ApiParam(name = "pageSize",value = "每页大小",required = true) @PathVariable("pageSize") long pageSize,
                                   PageQueryEduTeacherVo vo){
        log.debug("PageQueryEduTeacher {} {} {}",currentPage,pageSize,vo);

        QueryWrapper wrapper = new QueryWrapper();
        if(vo.getBeginTime()!=null){
            wrapper.ge("gmt_create", vo.getBeginTime());
        }
        if(vo.getEndTime()!=null){
            wrapper.le("gmt_create", vo.getEndTime());
        }
        if(!StringUtils.isEmpty(vo.getName())){
            wrapper.like("name", vo.getName());
        }
        if(!StringUtils.isEmpty(vo.getLevel())){
            wrapper.eq("level", vo.getLevel());
        }

        wrapper.orderByDesc("gmt_create");

        IPage<EduTeacherEntity> page = new Page<>(currentPage,pageSize);
        eduTeacherService.page(page, wrapper);
        Map<String,Object> map = new HashMap<>();
        map.put("total", page.getTotal());
        map.put("records", page.getRecords());
        return Res.ok().setData(map);
    }*/

   /* @GetMapping("/{id}")
    @ApiOperation("根据id查询讲师")
    public Res getEduTeacherById(@RequestHeader("username") String username, @ApiParam(name = "id",value = "讲师id",required = true) @PathVariable("id") String id){
        log.debug("getEduTeacherById {} {}",id,username);
        EduTeacherEntity eduTeacher = eduTeacherService.getById(id);
        return Res.ok().setData(eduTeacher);
    }*/

    @GetMapping("/{id}")
    @ApiOperation("根据id查询讲师")
    public Res getEduTeacherById(){
        log.info("getEduTeacherById");
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setId("123");
        addressEntity.setName("12312321");
        addressEntity.setCreateTime(new Date());
        addressEntity.setUpdateTime(new Date());
        return Res.ok().setData(addressEntity);
    }

    /*@GetMapping("/all")
    @ApiOperation("查询所有讲师")
    public Res getAllEduTeacher(){
        log.debug("getAllEduTeacher");
        List<EduTeacherEntity> list = eduTeacherService.list();
        return Res.ok().setData(list);
    }*/

}

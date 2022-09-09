package com.gzzn.service.edu.converter;

import com.gzzn.service.edu.entity.EduCourseEntity;
import com.gzzn.service.edu.vo.req.AddEduCourseVo;
import com.gzzn.service.edu.vo.req.UpdateEduCourseVo;
import com.gzzn.service.edu.vo.resp.QueryEduCourseVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EduCourseConverter {

    /*@Mapping(source = "birthday",target = "birth")
    @Mapping(source = "birthday",target = "birthDateFormat",dateFormat = "yyyy-HH-dd HH:mm:ss")
    @Mapping(target = "birthExpressionFormat",dateFormat = "yyyy-HH-dd HH:mm:ss")
    @Mapping(source = "email",target = "email",ignore = true)*/


    EduCourseConverter INSTANCE = Mappers.getMapper(EduCourseConverter.class);

    EduCourseEntity convert(AddEduCourseVo vo);

    QueryEduCourseVo convert(EduCourseEntity entity);

    EduCourseEntity convert(UpdateEduCourseVo vo);
}
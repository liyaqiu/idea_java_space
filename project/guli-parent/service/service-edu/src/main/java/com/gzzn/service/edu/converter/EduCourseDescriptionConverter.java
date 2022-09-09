package com.gzzn.service.edu.converter;

import com.gzzn.service.edu.entity.EduCourseDescriptionEntity;
import com.gzzn.service.edu.vo.req.AddEduCourseVo;
import com.gzzn.service.edu.vo.req.UpdateEduCourseVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EduCourseDescriptionConverter {

    /*@Mapping(source = "birthday",target = "birth")
    @Mapping(source = "birthday",target = "birthDateFormat",dateFormat = "yyyy-HH-dd HH:mm:ss")
    @Mapping(target = "birthExpressionFormat",dateFormat = "yyyy-HH-dd HH:mm:ss")
    @Mapping(source = "email",target = "email",ignore = true)*/


    EduCourseDescriptionConverter INSTANCE = Mappers.getMapper(EduCourseDescriptionConverter.class);

    EduCourseDescriptionEntity convert(AddEduCourseVo vo);

    EduCourseDescriptionEntity convert(UpdateEduCourseVo vo);
}
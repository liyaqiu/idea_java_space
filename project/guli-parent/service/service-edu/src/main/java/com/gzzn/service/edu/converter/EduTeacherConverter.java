package com.gzzn.service.edu.converter;

import com.gzzn.service.edu.vo.req.AddEduTeacherVo;
import com.gzzn.service.edu.vo.req.UpdateEduTeacherVo;
import com.gzzn.service.edu.entity.EduTeacherEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EduTeacherConverter {

    /*@Mapping(source = "birthday",target = "birth")
    @Mapping(source = "birthday",target = "birthDateFormat",dateFormat = "yyyy-HH-dd HH:mm:ss")
    @Mapping(target = "birthExpressionFormat",dateFormat = "yyyy-HH-dd HH:mm:ss")
    @Mapping(source = "email",target = "email",ignore = true)*/


    EduTeacherConverter INSTANCE = Mappers.getMapper(EduTeacherConverter.class);

    EduTeacherEntity convert(AddEduTeacherVo vo);

    EduTeacherEntity convert(UpdateEduTeacherVo vo);
}
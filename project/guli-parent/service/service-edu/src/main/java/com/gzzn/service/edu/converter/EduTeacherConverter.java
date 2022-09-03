package com.gzzn.service.edu.converter;

import com.gzzn.service.edu.dto.AddEduTeacherDto;
import com.gzzn.service.edu.dto.UpdateEduTeacherDto;
import com.gzzn.service.edu.entity.EduTeacherEntity;
import io.swagger.annotations.ApiModelProperty;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EduTeacherConverter {

    /*@Mapping(source = "birthday",target = "birth")
    @Mapping(source = "birthday",target = "birthDateFormat",dateFormat = "yyyy-HH-dd HH:mm:ss")
    @Mapping(target = "birthExpressionFormat",dateFormat = "yyyy-HH-dd HH:mm:ss")
    @Mapping(source = "email",target = "email",ignore = true)*/


    EduTeacherConverter INSTANCE = Mappers.getMapper(EduTeacherConverter.class);

    EduTeacherEntity convert(AddEduTeacherDto dto);

    EduTeacherEntity convert(UpdateEduTeacherDto dto);
}
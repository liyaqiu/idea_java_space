package com.gzzn.service.edu.converter;

import com.gzzn.service.edu.entity.EduChapterEntity;
import com.gzzn.service.edu.entity.EduVideoEntity;
import com.gzzn.service.edu.vo.req.AddEduChapterVo;
import com.gzzn.service.edu.vo.req.AddEduVideoVo;
import com.gzzn.service.edu.vo.req.UpdateEduVideoVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EduVideoConverter {

    /*@Mapping(source = "birthday",target = "birth")
    @Mapping(source = "birthday",target = "birthDateFormat",dateFormat = "yyyy-HH-dd HH:mm:ss")
    @Mapping(target = "birthExpressionFormat",dateFormat = "yyyy-HH-dd HH:mm:ss")
    @Mapping(source = "email",target = "email",ignore = true)*/


    EduVideoConverter INSTANCE = Mappers.getMapper(EduVideoConverter.class);

    EduVideoEntity convert(AddEduVideoVo vo);
    EduVideoEntity convert(UpdateEduVideoVo vo);
}
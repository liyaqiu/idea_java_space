package com.gzzn.service.acl.converter;

import com.gzzn.service.edu.entity.EduChapterEntity;
import com.gzzn.service.edu.vo.req.AddEduChapterVo;
import com.gzzn.service.edu.vo.req.UpdateEduChapterVo;
import com.gzzn.service.edu.vo.resp.QueryChapterVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface EduChapterConverter {

    /*@Mapping(source = "birthday",target = "birth")
    @Mapping(source = "birthday",target = "birthDateFormat",dateFormat = "yyyy-HH-dd HH:mm:ss")
    @Mapping(target = "birthExpressionFormat",dateFormat = "yyyy-HH-dd HH:mm:ss")
    @Mapping(source = "email",target = "email",ignore = true)*/


    EduChapterConverter INSTANCE = Mappers.getMapper(EduChapterConverter.class);

    EduChapterEntity convert(AddEduChapterVo vo);

    List<QueryChapterVo> convert(List<EduChapterEntity> list);

    EduChapterEntity convert(UpdateEduChapterVo vo);
}
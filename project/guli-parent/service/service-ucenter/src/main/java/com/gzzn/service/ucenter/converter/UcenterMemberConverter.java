package com.gzzn.service.ucenter.converter;

import com.gzzn.service.ucenter.entity.UcenterMemberEntity;
import com.gzzn.service.ucenter.vo.req.LoginVo;
import com.gzzn.service.ucenter.vo.req.RegisterVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UcenterMemberConverter {

    /*@Mapping(source = "birthday",target = "birth")
    @Mapping(source = "birthday",target = "birthDateFormat",dateFormat = "yyyy-HH-dd HH:mm:ss")
    @Mapping(target = "birthExpressionFormat",dateFormat = "yyyy-HH-dd HH:mm:ss")
    @Mapping(source = "email",target = "email",ignore = true)*/


    UcenterMemberConverter INSTANCE = Mappers.getMapper(UcenterMemberConverter.class);

    UcenterMemberEntity convert(LoginVo vo);
    UcenterMemberEntity convert(RegisterVo vo);
}
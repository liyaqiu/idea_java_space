package com.gzzn.service.crm.converter;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CrmBannerConverter {

    /*@Mapping(source = "birthday",target = "birth")
    @Mapping(source = "birthday",target = "birthDateFormat",dateFormat = "yyyy-HH-dd HH:mm:ss")
    @Mapping(target = "birthExpressionFormat",dateFormat = "yyyy-HH-dd HH:mm:ss")
    @Mapping(source = "email",target = "email",ignore = true)*/


    CrmBannerConverter INSTANCE = Mappers.getMapper(CrmBannerConverter.class);

}
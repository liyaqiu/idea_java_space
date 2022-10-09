package com.gzzn.service.acl.converter;

import com.gzzn.service.acl.entity.AclPermissionEntity;
import com.gzzn.service.acl.vo.req.AddAclPermissionVo;
import com.gzzn.service.acl.vo.req.UpdateAclPermissionVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AclPermissionConverter {

    /*@Mapping(source = "birthday",target = "birth")
    @Mapping(source = "birthday",target = "birthDateFormat",dateFormat = "yyyy-HH-dd HH:mm:ss")
    @Mapping(target = "birthExpressionFormat",dateFormat = "yyyy-HH-dd HH:mm:ss")
    @Mapping(source = "email",target = "email",ignore = true)*/


    AclPermissionConverter INSTANCE = Mappers.getMapper(AclPermissionConverter.class);


    AclPermissionEntity convert(AddAclPermissionVo vo);

    AclPermissionEntity convert(UpdateAclPermissionVo vo);

}
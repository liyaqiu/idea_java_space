package com.gzzn.service.acl.converter;

import com.gzzn.service.acl.entity.AclPermissionEntity;
import com.gzzn.service.acl.entity.AclRoleEntity;
import com.gzzn.service.acl.vo.req.AddAclPermissionVo;
import com.gzzn.service.acl.vo.req.AddAclRoleVo;
import com.gzzn.service.acl.vo.req.UpdateAclPermissionVo;
import com.gzzn.service.acl.vo.req.UpdateAclRoleVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AclRoleConverter {

    /*@Mapping(source = "birthday",target = "birth")
    @Mapping(source = "birthday",target = "birthDateFormat",dateFormat = "yyyy-HH-dd HH:mm:ss")
    @Mapping(target = "birthExpressionFormat",dateFormat = "yyyy-HH-dd HH:mm:ss")
    @Mapping(source = "email",target = "email",ignore = true)*/


    AclRoleConverter INSTANCE = Mappers.getMapper(AclRoleConverter.class);


    AclRoleEntity convert(AddAclRoleVo vo);

    AclRoleEntity convert(UpdateAclRoleVo vo);


}
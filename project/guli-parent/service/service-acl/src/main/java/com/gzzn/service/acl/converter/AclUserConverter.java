package com.gzzn.service.acl.converter;

import com.gzzn.service.acl.entity.AclRoleEntity;
import com.gzzn.service.acl.entity.AclUserEntity;
import com.gzzn.service.acl.vo.req.AddAclRoleVo;
import com.gzzn.service.acl.vo.req.AddAclUserVo;
import com.gzzn.service.acl.vo.req.UpdateAclRoleVo;
import com.gzzn.service.acl.vo.resp.AclUserVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface AclUserConverter {

    /*@Mapping(source = "birthday",target = "birth")
    @Mapping(source = "birthday",target = "birthDateFormat",dateFormat = "yyyy-HH-dd HH:mm:ss")
    @Mapping(target = "birthExpressionFormat",dateFormat = "yyyy-HH-dd HH:mm:ss")
    @Mapping(source = "email",target = "email",ignore = true)*/


    AclUserConverter INSTANCE = Mappers.getMapper(AclUserConverter.class);


    List<AclUserVo> convert(List<AclUserEntity> aclUserList);

    AclUserEntity convert(AddAclUserVo vo);
}
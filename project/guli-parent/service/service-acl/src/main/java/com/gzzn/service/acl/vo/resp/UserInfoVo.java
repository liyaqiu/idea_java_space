package com.gzzn.service.acl.vo.resp;

import com.gzzn.service.acl.entity.AclPermissionEntity;
import com.gzzn.service.acl.entity.AclUserEntity;
import lombok.Data;

import java.util.List;

/**
 * @author eric
 * @date 2022/10/7 14:43
 **/
@Data
public class UserInfoVo {
    AclUserEntity aclUser;
    List<AclPermissionEntity> menuTreeList;
    List<String> authorities;
}

package com.gzzn.service.acl.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gzzn.service.acl.entity.AclUserEntity;
import com.gzzn.service.acl.vo.resp.UserInfoVo;

/**
 * @author lyq
 * @date 2021/12/6 12:39
 */

public interface AclUserService extends IService<AclUserEntity> {

    UserInfoVo getUserInfoByUsername(String username);
}

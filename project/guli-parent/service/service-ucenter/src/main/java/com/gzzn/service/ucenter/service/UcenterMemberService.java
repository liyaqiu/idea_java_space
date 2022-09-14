package com.gzzn.service.ucenter.service;

import com.baomidou.mybatisplus.extension.service.IService;

import com.gzzn.service.ucenter.entity.UcenterMemberEntity;
import com.gzzn.service.ucenter.vo.req.RegisterVo;

import java.util.Map;

/**
 * @author lyq
 * @date 2021/12/6 12:39
 */

public interface UcenterMemberService extends IService<UcenterMemberEntity> {

    Map<String,Object> ssoLogin(UcenterMemberEntity ucenterMember);

    UcenterMemberEntity getMemberInfo(String token);

    void register(RegisterVo vo);
}

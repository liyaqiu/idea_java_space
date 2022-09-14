package com.gzzn.service.ucenter.service.impl;

import cn.hutool.crypto.SecureUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gzzn.service.common.utils.JWTUtil;
import com.gzzn.service.common.utils.Res;
import com.gzzn.service.ucenter.client.ServiceMsmClient;
import com.gzzn.service.ucenter.converter.UcenterMemberConverter;
import com.gzzn.service.ucenter.entity.UcenterMemberEntity;
import com.gzzn.service.ucenter.mapper.UcenterMemberMapper;
import com.gzzn.service.ucenter.service.UcenterMemberService;
import com.gzzn.service.ucenter.vo.req.RegisterVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lyq
 * @date 2021/12/6 12:43
 */
@Service
@Slf4j
public class UcenterMemberServiceImpl extends ServiceImpl<UcenterMemberMapper, UcenterMemberEntity> implements UcenterMemberService {

    @Autowired
    private UcenterMemberMapper ucenterMemberMapper;
    @Autowired
    private ServiceMsmClient serviceMsmClient;

    @Override
    public Map<String,Object> ssoLogin(UcenterMemberEntity ucenterMember) {
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("mobile", ucenterMember.getMobile());
        wrapper.eq("password", SecureUtil.md5(ucenterMember.getPassword()));
        ucenterMember = ucenterMemberMapper.selectOne(wrapper);
        if(ucenterMember==null){
            throw new RuntimeException("用户或密码错误");
        }
        if(ucenterMember.getIsDisabled()==1){
            throw new RuntimeException("该用户已经被禁用");
        }

        String token = JWTUtil.createToken(ucenterMember.getId());

        Map<String,Object> map = new HashMap<>();
        map.put("token", token);
        return map;
    }

    @Override
    public UcenterMemberEntity getMemberInfo(String token) {
        try {
            JWTUtil.verifyToken(token);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        String id = (String) JWTUtil.parseToken(token);
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("id", id);
        wrapper.select("nickname","avatar");
        UcenterMemberEntity ucenterMember = ucenterMemberMapper.selectOne(wrapper);
        if(ucenterMember==null){
            throw new RuntimeException("用户不存在");
        }
        return ucenterMember;
    }

    @Override
    public void register(RegisterVo vo) {
        UcenterMemberEntity ucenterMember = UcenterMemberConverter.INSTANCE.convert(vo);
        ucenterMember.setGmtCreate(new Date());
        ucenterMember.setGmtModified(new Date());

        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq("mobile", ucenterMember.getMobile());
        if (ucenterMemberMapper.selectOne(wrapper)!=null) {
            throw new RuntimeException("该手机号已存在");
        }

        String code = vo.getCode();
        Res res = serviceMsmClient.verifyCode(vo.getMobile(), code);
        if(res==null){
            throw new RuntimeException("短信服务模块请求失败");
        }
        if (!res.isSuccess()) {
            throw new RuntimeException(res.getMessage());
        }
        if (ucenterMemberMapper.insert(ucenterMember)!=1) {
            throw new RuntimeException("注册失败");
        }
    }
}

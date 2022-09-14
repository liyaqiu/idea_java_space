package com.gzzn.service.msm.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import com.gzzn.service.msm.service.MsmService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author lyq
 * @date 2021/12/6 12:43
 */
@Service
@Slf4j
public class MsmServiceImpl implements MsmService {

    @Autowired
    StringRedisTemplate redisTemplate;

    @Override
    public Map<String, Object> sendCode(String mobile) {
        //TODO 这里应该调用阿里短信接口，此处做了模拟
        String fourBitCode = RandomUtil.randomInt(1000, 9999)+"";

        String redisKey = "mobile."+mobile;

        if(redisTemplate.opsForValue().get(redisKey)!=null){
            throw new RuntimeException("请在5分钟后在操作");
        }
        redisTemplate.opsForValue().set( redisKey,fourBitCode,5, TimeUnit.MINUTES);

        Map<String,Object> map = new HashMap<>();
        map.put("code", fourBitCode);
        return map;
    }

    @Override
    public void verifyCode(String mobile, String code) {
        String redisKey = "mobile."+mobile;
        String oldCode = redisTemplate.opsForValue().get(redisKey);
        if(oldCode==null){
            throw new RuntimeException("验证码不正确");
        }
        if(!code.equals(oldCode)){
            throw new RuntimeException("验证码不正确");
        }
    }

}

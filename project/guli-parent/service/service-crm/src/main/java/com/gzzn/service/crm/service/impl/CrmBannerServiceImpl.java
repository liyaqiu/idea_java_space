package com.gzzn.service.crm.service.impl;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gzzn.service.crm.entity.CrmBannerEntity;
import com.gzzn.service.crm.mapper.CrmBannerMapper;
import com.gzzn.service.crm.service.CrmBannerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author lyq
 * @date 2021/12/6 12:43
 */
@Service
@Slf4j
public class CrmBannerServiceImpl extends ServiceImpl<CrmBannerMapper, CrmBannerEntity> implements CrmBannerService {

    @Autowired
    CrmBannerMapper crmBannerMapper;
    @Autowired
    StringRedisTemplate redisTemplate;




    @Override
    public List<CrmBannerEntity> queryBanner() {
        String key = "banner.list";
        List<CrmBannerEntity> crmBannerList = null;
        String jsonListStr = redisTemplate.opsForValue().get(key);
        if(jsonListStr==null){
            crmBannerList = crmBannerMapper.selectList(null);
            redisTemplate.opsForValue().set(key, JSONObject.toJSONString(crmBannerList),5, TimeUnit.MINUTES);
            return crmBannerList;
        }
        crmBannerList =  JSONObject.parseArray(jsonListStr, CrmBannerEntity.class);
        return crmBannerList;
    }
}

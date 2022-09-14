package com.gzzn.service.crm.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gzzn.service.crm.entity.CrmBannerEntity;

import java.util.List;

/**
 * @author lyq
 * @date 2021/12/6 12:39
 */

public interface CrmBannerService extends IService<CrmBannerEntity> {

    List<CrmBannerEntity> queryBanner();
}

package com.gzzn.service.crm.controller.frontend;

import com.gzzn.service.crm.entity.CrmBannerEntity;
import com.gzzn.service.crm.service.CrmBannerService;
import com.gzzn.service.utils.Res;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author lyq
 * @date 2021/12/4 12:33
 */
@RestController
@RequestMapping("/crm/frontend/banner")
@Slf4j
@Api(tags = {"横幅管理"})
public class CrmBannerController {

    @Autowired
    private CrmBannerService crmBannerService;

    @GetMapping("/all")
    @ApiOperation("查询所有横幅")
    public Res queryBanner(){
        log.debug("queryBanner");
        List<CrmBannerEntity> list =  crmBannerService.queryBanner();
        return Res.ok().setData(list);
    }




}

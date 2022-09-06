package com.gzzn.service.oss.controller;

import com.gzzn.service.oss.service.OssService;
import com.gzzn.test.service.common.utils.Res;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

/**
 * @author lyq
 * @date 2021/12/4 12:33
 */
@RestController
@RequestMapping("/oss")
@Slf4j
@Api(tags = {"文件管理"})
//@CrossOrigin
public class OssController {

    @Autowired
    private OssService ossService;

    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Res uploadFile(MultipartFile file){
        log.debug("uploadFile {}",file);
        String fileUrl = ossService.upload(file);
        Map<String,String> map = new HashMap<>();
        map.put("url",fileUrl);
        return Res.ok().setData(map);
    }

}

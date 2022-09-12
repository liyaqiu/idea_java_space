package com.gzzn.service.oss.controller;

import com.gzzn.service.oss.service.OssService;
import com.gzzn.service.common.utils.Res;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
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
        Map<String,String> result = ossService.upload(file);
        return Res.ok().setData(result);
    }

    @PostMapping("/video")
    @ApiOperation("视频上传")
    public Res uploadVideo(MultipartFile file){
        log.debug("uploadVideo {}",file);
        Map<String,String> result = ossService.uploadVideo(file);
        return Res.ok().setData(result);
    }

    @DeleteMapping("/video/{filename}")
    @ApiOperation("视频删除")
    public Res removeVideo(@PathVariable("filename") String objectName){
        log.debug("removeFile {}",objectName);
        ossService.removeFile(objectName);
        return Res.ok();
    }

}

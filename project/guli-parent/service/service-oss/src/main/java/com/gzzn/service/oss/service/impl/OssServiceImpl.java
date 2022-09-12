package com.gzzn.service.oss.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.gzzn.service.oss.service.OssService;
import com.gzzn.service.common.exception.FileUploadException;
import com.gzzn.service.oss.config.OSSConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author lyq
 * @date 2021/12/6 12:43
 */
@Service
@Slf4j
public class OssServiceImpl implements OssService {

    @Autowired
    OSSConfig ossConfig;

    @Override
    public Map<String, String> upload(MultipartFile file) {
        String endpoint = ossConfig.getEndpoint();
        String accessKeyId = ossConfig.getAccessKeyId();
        String accessKeySecret = ossConfig.getAccessKeySecret();
        String bucketName = ossConfig.getBucketName();

        // 填写Object完整路径，完整路径中不能包含Bucket名称，例如exampledir/exampleobject.txt。
        String objectName = DateUtil.format(new Date(), "yyyy/MM/dd/")+IdUtil.simpleUUID()+"-"+file.getOriginalFilename();

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            // 创建PutObject请求。
            ossClient.putObject(bucketName, objectName, file.getInputStream());

            //https://liyaqiu-bucket.oss-cn-hangzhou.aliyuncs.com/2022/09/06/700e8db58d5b4240abff15f3f978e488-nihao.png
            String url = "https://"+bucketName+"."+endpoint+"/"+objectName;
            Map<String,String> map = new HashMap<>();
            map.put("url",url);
            return map;
        } catch (Exception e) {
            throw new FileUploadException(e);
        }finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    @Override
    public void removeFile(String objectName) {
        String endpoint = ossConfig.getEndpoint();
        String accessKeyId = ossConfig.getAccessKeyId();
        String accessKeySecret = ossConfig.getAccessKeySecret();
        String bucketName = ossConfig.getBucketName();

        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

        try {
            // 删除文件或目录。如果要删除目录，目录必须为空。
            ossClient.deleteObject(bucketName, objectName+".png");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }

    @Override
    public Map<String, String> uploadVideo(MultipartFile file) {
        String endpoint = ossConfig.getEndpoint();
        String accessKeyId = ossConfig.getAccessKeyId();
        String accessKeySecret = ossConfig.getAccessKeySecret();
        String bucketName = ossConfig.getBucketName();

        String uuid = IdUtil.simpleUUID();


        String videoOriginalName = uuid+"-"+file.getOriginalFilename().split("\\.")[0];
        String objectName = uuid+"-"+file.getOriginalFilename();
        // 创建OSSClient实例。
        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        try {
            // 创建PutObject请求。
            ossClient.putObject(bucketName, objectName, file.getInputStream());

            //https://liyaqiu-bucket.oss-cn-hangzhou.aliyuncs.com/2022/09/06/700e8db58d5b4240abff15f3f978e488-nihao.png
            String url = "https://"+bucketName+"."+endpoint+"/"+objectName;
            Map<String,String> map = new HashMap<>();
            map.put("videoSourceId",url);
            map.put("videoOriginalName",videoOriginalName);
            return map;
        } catch (Exception e) {
            throw new FileUploadException(e);
        }finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}

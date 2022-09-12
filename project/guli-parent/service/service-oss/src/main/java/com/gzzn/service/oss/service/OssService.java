package com.gzzn.service.oss.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * @author lyq
 * @date 2021/12/6 12:39
 */

public interface OssService {
    Map<String, String> upload(MultipartFile file);

    void removeFile(String objectName);

    Map<String, String> uploadVideo(MultipartFile file);
}

package com.gzzn.service.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author lyq
 * @date 2021/12/6 12:39
 */

public interface OssService {
    String upload(MultipartFile file);
}

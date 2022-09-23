package com.controller文件上传;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;


@RestController
@RequestMapping("/file")
@Slf4j
public class FileUploadController1 {



    @PostMapping("/upload")
    public void test1(String name, String age, MultipartFile file1) throws IOException {
        log.info("name :{},age: {}",name,age);
        log.info("fileName:{}",file1.getOriginalFilename());
        log.info("file1:{}",file1);
        file1.transferTo(new File("E:\\"+file1.getOriginalFilename()));
    }



}

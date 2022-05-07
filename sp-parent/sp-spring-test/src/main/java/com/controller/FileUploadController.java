package com.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/file")
@Slf4j
public class FileUploadController {



    @PostMapping("/upload")
    public void test1(String name, String age, MultipartFile file1) throws IOException {
        log.info("name :{},age: {}",name,age);
        log.info("fileName:{}",file1.getOriginalFilename());
        log.info("file1:{}",file1);
        file1.transferTo(new File("E:\\"+file1.getOriginalFilename()));
    }



}

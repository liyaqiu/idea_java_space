package com.gzzn.service.edu.controller;

import com.gzzn.service.edu.entity.EduTeacher;
import com.gzzn.service.edu.service.EduTeacherService;
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
@RequestMapping("/EduTeacher")
@Slf4j
public class EduTeacherController {

    @Autowired
    private EduTeacherService  eduTeacherService;


    @GetMapping("/test")
    //@PostMapping("/test")
    //@PutMapping("/test")
    //@DeleteMapping("/test")
    public List<EduTeacher> test(){
        log.debug("hello");
        List<EduTeacher> list = eduTeacherService.list();
        return list;
    }


}

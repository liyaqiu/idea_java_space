package com.fm.controller;

import com.fm.entity.Person;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.OutputStreamWriter;
import java.io.PrintStream;

@RestController
@RequestMapping("/fm")
@Slf4j
public class FmController {

    @Autowired
    Configuration configuration;

    @GetMapping("/test01")
    public void test01(HttpServletResponse response) throws Exception {
        log.info("123123{}",configuration);
        configuration.setDirectoryForTemplateLoading(new File(FmController.class.getClassLoader().getResource("").getPath()+"template"));
        configuration.setDefaultEncoding("utf-8");
        Template template = configuration.getTemplate("test01.ftl");
        template.process(new Person("helloworld"),new OutputStreamWriter(System.out));
    }
}

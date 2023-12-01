package com.fm._2指令;

import com.fm.controller.FmController;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
import java.io.OutputStreamWriter;

@Slf4j
public class Demo2 {

    @Test
    public void test01() throws Exception {
        Configuration configuration = new Configuration();
        configuration.setDirectoryForTemplateLoading(new File(FmController.class.getClassLoader().getResource("").getPath()+"template"));
        configuration.setDefaultEncoding("utf-8");
        Template template = configuration.getTemplate("demo2.ftl");
        template.process(new Person(),new OutputStreamWriter(System.out));
    }
}

package com.获取类_方法上注解;

import cn.hutool.core.annotation.AnnotationUtil;
import com.bean.TestObj;
import com.bean.UserEntity;
import lombok.Data;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.AnnotatedElementUtils;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.*;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

/**
 * @author lyq
 * @date 2022/2/26 9:21
 * @RequestBody 接收json数据，默认为表单数据
 * @ResponseBody json数据返回或者字符串返回，默认为转发页面
 */

@RestController
@RequestMapping("/TestControllerAnno")
@Slf4j
public class TestControllerAnno {

    @Autowired
    ApplicationContext applicationContext;

    @GetMapping("test01")
    public void test01(){
        String[] beanNames = applicationContext.getBeanNamesForType(FatherAnno.class);

        for (String beanName : beanNames) {
            FatherAnno fatherAnno = (FatherAnno) applicationContext.getBean(beanName);
            //判断类上的注解
            boolean b = AnnotatedElementUtils.hasAnnotation(fatherAnno.getClass(), MyClassAnno.class);
            System.out.println("这个类是否有注解 "+b);

            if(b){
                //如果有这个注解，获取注解值
                MyClassAnno myClassAnno = AnnotationUtil.getAnnotation(fatherAnno.getClass(),MyClassAnno.class);
                System.out.println(myClassAnno.value()+"   "+myClassAnno.name());

                MyClassAnnoFather myClassAnnoFather = AnnotationUtil.getAnnotation(MyClassAnno.class,MyClassAnnoFather.class);
                System.out.println(myClassAnnoFather.value()+"   "+myClassAnnoFather.name());
            }


            //判断方法上的注解
            /*Method[] methods = fatherAnno.getClass().getMethods();
            for (Method method : methods) {
                System.out.println(method.getName()+"  "+AnnotatedElementUtils.hasAnnotation(method,MyMethodAnno.class));
            }*/
        }
    }

}

interface FatherAnno{
    void haha();
}

@Component
@MyClassAnno()
class Son1Anno implements FatherAnno{
    @MyMethodAnno
    @Override
    public void haha() {

    }
}

@Component
class Son2Anno implements FatherAnno{
    @Override
    public void haha() {

    }
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface MyClassAnnoFather {
    String value() default "默认Father value";
    String name() default "默认Father name";
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@MyClassAnnoFather
@interface MyClassAnno {
    String value() default "默认son value";
    String name() default "默认son name";
}

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface MyMethodAnno{}

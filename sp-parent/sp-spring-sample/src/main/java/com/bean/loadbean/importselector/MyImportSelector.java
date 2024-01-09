package com.bean.loadbean.importselector;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author eric
 * @date 2022/5/11 13:15
 **/

@Slf4j
public class MyImportSelector implements ImportSelector {
    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        log.info("当前开启了注解的类:"+annotationMetadata.getClassName());
        log.info(("是否开启的注解:"+annotationMetadata.hasAnnotation("org.springframework.stereotype.Component")));
        if(annotationMetadata.hasAnnotation("org.springframework.stereotype.Component")){
            return new String[]{"com.loadbean.importselector.Selector1"};
        }else{
            return new String[]{"com.loadbean.importselector.Selector2"};
        }

    }
}

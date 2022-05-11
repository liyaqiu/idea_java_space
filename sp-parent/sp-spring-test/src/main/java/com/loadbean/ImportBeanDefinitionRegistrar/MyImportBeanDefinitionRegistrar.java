package com.loadbean.ImportBeanDefinitionRegistrar;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @author eric
 * @date 2022/5/11 13:56
 **/
@Slf4j
public class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {
    @Override
    public void registerBeanDefinitions(AnnotationMetadata annotationMetadata, BeanDefinitionRegistry registry) {
        log.info("当前开启了注解的类:"+annotationMetadata.getClassName());
        log.info(("是否开启的注解:"+annotationMetadata.hasAnnotation("org.springframework.stereotype.Component")));
        if(annotationMetadata.hasAnnotation("org.springframework.stereotype.Component")){
            registry.registerBeanDefinition("DefinitionEntity1", BeanDefinitionBuilder.rootBeanDefinition(DefinitionEntity1.class).getBeanDefinition());
        }else{
            registry.registerBeanDefinition("DefinitionEntity2", BeanDefinitionBuilder.rootBeanDefinition(DefinitionEntity2.class).getBeanDefinition());
        }


    }
}

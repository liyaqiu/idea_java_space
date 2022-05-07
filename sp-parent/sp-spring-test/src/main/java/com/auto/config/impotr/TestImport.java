package com.auto.config.impotr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;

/**
 * @author eric
 * @date 2022/3/17 14:30
 **/


@Component
@Import({
        ImportBean.class, //加载bean
        ImportConfig.class,//加载配置
        MyImportSelector.class, //实现ImportSelector
        MyImportBeanDefinitionRegistrar.class
}
)
public class TestImport {

    @Autowired
    ImportBean importBean;
    @Autowired
    ImportConfig importConfig;

    @Autowired
    MyImportSelector.AA aa;
    @Autowired
    MyImportSelector.BB bb;

    @Autowired
    @Qualifier("ccName")
    MyImportBeanDefinitionRegistrar.CC cc;
}

class ImportBean {
}

@ConfigurationProperties(prefix = "args")
class ImportConfig {
}


class MyImportSelector implements ImportSelector {

    public static class AA {
    }

    public static class BB {
    }

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{"com.auto.config.impotr.MyImportSelector.AA", "com.auto.config.impotr.MyImportSelector.BB"};
    }
}

class MyImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar {

    public static class CC {
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        registry.registerBeanDefinition("ccName", BeanDefinitionBuilder.rootBeanDefinition(CC.class).getBeanDefinition());
    }
}
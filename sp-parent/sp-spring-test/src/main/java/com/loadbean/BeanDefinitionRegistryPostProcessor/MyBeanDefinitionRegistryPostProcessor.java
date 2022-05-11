package com.loadbean.BeanDefinitionRegistryPostProcessor;

import com.loadbean.ImportBeanDefinitionRegistrar.DefinitionEntity1;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;

/**
 * @author eric
 * @date 2022/5/11 14:34
 * 这种方式可以覆盖前面所有的bean注册
 **/
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        registry.registerBeanDefinition("PostProcessorEntity1", BeanDefinitionBuilder.rootBeanDefinition(PostProcessorEntity1.class).getBeanDefinition());
        registry.registerBeanDefinition("PostProcessorEntity2", BeanDefinitionBuilder.rootBeanDefinition(PostProcessorEntity2.class).getBeanDefinition());
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}

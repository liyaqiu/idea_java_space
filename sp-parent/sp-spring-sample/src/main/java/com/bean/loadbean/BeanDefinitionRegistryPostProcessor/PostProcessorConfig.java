package com.bean.loadbean.BeanDefinitionRegistryPostProcessor;

import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * @author eric
 * @date 2022/5/11 14:35
 **/
@Component
@Import({MyBeanDefinitionRegistryPostProcessor.class})
public class PostProcessorConfig {
}

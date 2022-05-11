package com.loadbean.ImportBeanDefinitionRegistrar;

import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

/**
 * @author eric
 * @date 2022/5/11 13:56
 **/
@Component
@Import(MyImportBeanDefinitionRegistrar.class)
public class DefinitionConfig {
}

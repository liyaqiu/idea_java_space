package com.datavalid;

import org.hibernate.validator.BaseHibernateValidatorConfiguration;
import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;

import javax.validation.Validation;
import javax.validation.Validator;

/**
 * @author eric
 * @date 2022/9/23 12:23
 **/
@Configuration
public class ValidatorConfig {
    @Bean
    public Validator validator(){
        return Validation.byProvider(HibernateValidator.class).configure()
                //设置快速失败，如果有其中一个校验失败，立即停止校验
                .addProperty(BaseHibernateValidatorConfiguration.FAIL_FAST, Boolean.TRUE.toString()).buildValidatorFactory().getValidator();
    }

    /*
        开启快速失败返回
        如果参数校验有一场，直接抛异常，不会进入controller,使用全局异常拦截进行拦截
    */
    @Bean
    public MethodValidationPostProcessor test(){
        MethodValidationPostProcessor processor = new MethodValidationPostProcessor();
        processor.setValidator(validator());
        return processor;
    }
}

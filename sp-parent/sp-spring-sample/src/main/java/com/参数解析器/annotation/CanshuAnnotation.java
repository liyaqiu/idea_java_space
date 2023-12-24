package com.参数解析器.annotation;

import java.lang.annotation.*;

/**
 * @author eric
 * @date 2022/9/23 18:29
 **/
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CanshuAnnotation {
}

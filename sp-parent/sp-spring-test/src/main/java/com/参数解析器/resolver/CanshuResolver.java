package com.参数解析器.resolver;

import com.参数解析器.annotation.CanshuAnnotation;
import com.参数解析器.entity.Canshu;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;

/**
 * @author eric
 * @date 2022/9/23 18:26
 **/
@Slf4j
public class CanshuResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter param) {
        log.info("{}","supportsParameter");
        //判断类型，以及需要拥有注解才能行
        if(param.getParameterType() == Canshu.class && param.hasParameterAnnotation(CanshuAnnotation.class)){
            return true;
        }
        return false;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        log.info("{} {}","resolveArgument",request.getHeader("User-Agent"));
        Canshu canshu = new Canshu();
        canshu.setName(request.getHeader("User-Agent"));
        canshu.setAge(10);
        return canshu;
    }
}

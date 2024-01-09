package com.aop.demo;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @author lyq
 * @date 2022/3/2 2:28
 *
 */
@Aspect //切面类
@Component
//@Order(1)
@Slf4j
public class GetUserIdAspect {
    /*@Pointcut("@annotation(com.aop.demo.GetUserId)")//切入点
    public void GetUserId(){}//切点名称


    @Around("GetUserId()") //执行中的连接点 = 切入点
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("环绕通知 ----> 目标方法执行之前");

        MethodSignature methodSignature = (MethodSignature) (joinPoint.getSignature());
        Method method = methodSignature.getMethod();
        //思路，用反射拿方法参数的名字和参数类型在注入
        GetUserId annotation = method.getAnnotation(GetUserId.class);
        log.info("annotation {}",annotation.value());
        Object[] args = joinPoint.getArgs();
        args[0] = "12345678";
        Object obj = joinPoint.proceed(args);
        return obj;
    }*/



    @Around("@annotation(com.aop.demo.GetUserId)") //执行中的连接点 = 切入点
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("环绕通知 ----> 目标方法执行之前");

        MethodSignature methodSignature = (MethodSignature) (joinPoint.getSignature());
        Method method = methodSignature.getMethod();
        //思路，用反射拿方法参数的名字和参数类型在注入
        GetUserId annotation = method.getAnnotation(GetUserId.class);
        log.info("annotation {}",annotation.value());
        Object[] args = joinPoint.getArgs();
        args[0] = "12345678";
        Object obj = joinPoint.proceed(args);
        return obj;
    }

}

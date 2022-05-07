package com.aop.jdk;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author lyq
 * @date 2022/3/2 2:28
 *
 */
@Aspect //切面类
@Component
@Slf4j
public class MyAspect {
    @Pointcut("execution(public * com.aop.jdk.proxyobj..*.*(..))")//切入点
    public void pointcutName(){}//切点名称

    // execution([修饰符] 返回值类型 包名.类名.方法名(参数)) 从右往左匹配
    // 一个点代表当前包下，两个点表示当前包及其子包下
    // 参数两个点表示任意参数，任意类型
    @Pointcut("execution(public * com.aop.jdk.proxyobj2..*.*(..))")//切入点
    public void pointcutName2(){}//切点名称

    @Before("pointcutName() || pointcutName2()") //需要做干预的切点
    public void testBefore(){
        log.info("before...");
    }

    // advice
    //@Before("execution(public * com.aop.cglib.proxyobj2..*.*(..))")
    @Before("pointcutName()") //需要做干预的切点
    public void before(){
        log.info("前置通知 ----> 目标方法执行之前");
    }

    @AfterReturning("pointcutName()")
    public void afterReturning(){
        log.info("后置通知 ----> 目标方法执行之后");
    }

    @Around("pointcutName()") //执行中的连接点 = 切入点
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("环绕通知 ----> 目标方法执行之前");
        Object obj = joinPoint.proceed();
        log.info("环绕通知 ----> 目标方法执行之后");
        return obj;
    }

    @AfterThrowing("pointcutName()")
    public void afterThrowing(){
        log.info("异常通知 ----> 目标方法执行异常之后");
    }


    @After("pointcutName()")
    public void after(){
        log.info("最终通知 ----> 任何异常都会执行");
    }

}

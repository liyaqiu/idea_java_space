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
//@Order(1)
@Slf4j
public class MyAspect {
    @Pointcut("execution(public * com.aop.jdk.proxyobj..*.*(..))")//切入点
    public void pointcutName(){}//切点名称

    // execution([修饰符] 返回值类型 包名.类名.方法名(参数)) 从右往左匹配
    // 一个点代表当前包下，两个点表示当前包及其子包下
    // 参数两个点表示任意参数，任意类型
    @Pointcut("execution(public * com.aop.jdk.proxyobj2..*.*(..))")//切入点
    public void pointcutName2(){}//切点名称

    /*ruoyi利用注解作为切点
    execution：用于匹配方法执行的连接点；
    within：用于匹配指定类型内的方法执行；
            this：用于匹配当前AOP代理对象类型的执行方法；注意是AOP代理对象的类型匹配，这样就可能包括引入接口也类型匹配；
    target：用于匹配当前目标对象类型的执行方法；注意是目标对象的类型匹配，这样就不包括引入接口也类型匹配；
    args：用于匹配当前执行的方法传入的参数为指定类型的执行方法；
    @within：用于匹配所以持有指定注解类型内的方法；
    @target：用于匹配当前目标对象类型的执行方法，其中目标对象持有指定的注解；
    @args：用于匹配当前执行的方法传入的参数持有指定注解的执行；
    @annotation：用于匹配当前执行方法持有指定注解的方法；*/
    /*@Pointcut("@annotation(com.ruoyi.common.annotation.DataSource)"
            + "|| @within(com.ruoyi.common.annotation.DataSource)")
    public void dsPointCut() {}

    */

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

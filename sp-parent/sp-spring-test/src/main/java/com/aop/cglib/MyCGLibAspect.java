package com.aop.cglib;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.connector.RequestFacade;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * @author lyq
 * @date 2022/3/2 2:28
 *
 */
@Aspect //切面类
@Component
@Slf4j
public class MyCGLibAspect {
    //@Pointcut("execution(public * com.aop.cglib.proxyobj.TargetImpl3.sayHello(java.lang.String, int))")//切入点
    @Pointcut("execution(public * com.aop.cglib.proxyobj..*.*(..))")//切入点
    public void pointcutName3(){}//切点名称

    //切点表达式规则
    // execution([修饰符] 返回值类型 包名.类名.方法名(参数)) 从右往左匹配
    // 一个点代表当前包下，两个点表示当前包及其子包下
    // 参数两个点表示任意参数，任意类型
    @Pointcut("execution(public * com.aop.cglib.proxyobj2..*.*(..))")//切入点
    public void pointcutName4(){}//切点名称

    // advice
    //@Before("execution(public * com.aop.cglib.proxyobj2..*.*(..))")
    @Before("pointcutName3() || pointcutName4()") //需要做干预的切点
    public void before(JoinPoint joinPoint){
        log.info("ProceedingJoinPoint {}",joinPoint.getClass());
        log.info("className {}",joinPoint.getTarget().getClass().getName());
        log.info("methodName {}",joinPoint.getSignature().getName());
        for (Object arg : joinPoint.getArgs()) {
            log.info("arg {}",arg);
        }

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        log.info("前置通知 ----> 目标方法执行之前 {}",request.getRequestURL());
    }

    @AfterReturning("pointcutName3()")
    public void afterReturning(){
        log.info("后置通知 ----> 目标方法执行之后");
    }

    @Around("pointcutName3()") //执行中的连接点 = 切入点
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {

        log.info("ProceedingJoinPoint {}",joinPoint.getClass());
        log.info("className {}",joinPoint.getTarget().getClass().getName());
        log.info("methodName {}",joinPoint.getSignature().getName());
        for (Object arg : joinPoint.getArgs()) {
            log.info("arg {}",arg);
        }

        log.info("环绕通知 ----> 目标方法执行之前");
        Object obj = joinPoint.proceed();
        log.info("环绕通知 ----> 目标方法执行之后");
        return obj;
    }

    @AfterThrowing("pointcutName3()")
    public void afterThrowing(){
        log.info("异常通知 ----> 目标方法执行异常之后");
    }

    @After("pointcutName3()")
    public void after(){
        log.info("最终通知 ----> 任何异常都会执行");
    }


}

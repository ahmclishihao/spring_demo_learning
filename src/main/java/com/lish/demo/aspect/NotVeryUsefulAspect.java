package com.lish.demo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;

import java.util.Arrays;

/**
 * 一个切面
 * @author lish [devlishihao@gmail.com]
 * @date 18-5-9
 */
@Aspect
public class NotVeryUsefulAspect {

    // 切入点表达式
    @Pointcut("execution (public * com.lish.demo.aspect.demo.*.*(..))")
    public void anyOldTransfer(){
    }

    @Before(value = "anyOldTransfer() && target(target) && @annotation(needPermission)",argNames = "target,needPermission")
    public void checkPermission(Object target,NeedPermission needPermission){
        System.out.println(target.getClass().getSimpleName() + " 需要 " + needPermission.role() + " 角色 "+ Arrays.toString(needPermission.value()));
    }

    @Before(value = "anyOldTransfer() && @annotation(needLog) ",argNames = "joinPoint,needLog")
    public void before(JoinPoint joinPoint,NeedLog needLog){
        System.out.printf("info：%s \n",needLog.value());
    }

    @AfterReturning(value = "anyOldTransfer()",returning = "retVal")
    public void afterReturn(JoinPoint joinPoint,Object retVal){
        System.out.println("afterReturn");
    }

    @AfterThrowing(value = "anyOldTransfer()",throwing = "exp")
    public void afterThrowing(JoinPoint joinPoint,Throwable exp){
        System.out.println("afterThrowing");
    }

    @After(value = "anyOldTransfer()")
    public void after(JoinPoint joinPoint){
        System.out.println("after");
    }

    @Around(value = "anyOldTransfer()")
    public Object around(ProceedingJoinPoint joinPoint){
        System.out.println("Around before");
        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
            System.out.println("Around afterReturning");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            System.out.println("Around afterThrowing");
        }
        System.out.println("Around after");
        return proceed;
    }

}

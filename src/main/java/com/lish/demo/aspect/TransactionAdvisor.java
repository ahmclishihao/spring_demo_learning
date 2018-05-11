package com.lish.demo.aspect;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.support.DynamicMethodMatcherPointcut;

import java.lang.reflect.Method;

/**
 * 声明式事务
 * @author lish [devlishihao@gmail.com]
 * @date 18-5-10
 */
public class TransactionAdvisor implements PointcutAdvisor,MethodBeforeAdvice {


    @Override
    public Advice getAdvice() {
        return this;
    }

    @Override
    public boolean isPerInstance() {
        return true;
    }

    @Override
    public void before(Method method, Object[] args, Object target) throws Throwable {

    }

    @Override
    public Pointcut getPointcut() {
        return new AspectJExpressionPointcut();
    }
}

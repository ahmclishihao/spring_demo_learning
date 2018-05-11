package com.lish.demo.aspect;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;

/**
 * @author lish [devlishihao@gmail.com]
 * @date 18-5-10
 */
public class TestServiceAdvisor implements Advisor {
    @Override
    public Advice getAdvice() {
        return null;
    }

    @Override
    public boolean isPerInstance() {
        return false;
    }
}

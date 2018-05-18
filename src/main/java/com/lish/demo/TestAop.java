package com.lish.demo;

import com.lish.demo.aop.ServiceImpl;
import com.lish.demo.aop.ServiceInterface;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.math.BigDecimal;

/**
 * 测试基于接口的动态代理
 * @author lish [devlishihao@gmail.com]
 * @date 18-5-8
 */
public class TestAop {

    public static void main(String[] args) {
        ServiceInterface service = (ServiceInterface) Proxy.newProxyInstance(
                    ServiceImpl.class.getClassLoader(),
                    ServiceImpl.class.getInterfaces(),
                    new ProxyHandler(new ServiceImpl()));

        Proxy proxy = (Proxy) service;

        InvocationHandler invocationHandler = proxy.getInvocationHandler(proxy);
        Proxy.isProxyClass(service.getClass());


        service.pay(new BigDecimal("100000000"));

        System.out.println();
    }

    static class ProxyHandler implements InvocationHandler{

        private Object target;

        public ProxyHandler(Object target) {
            this.target = target;
        }

        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("pre aop");
            Object result = null;
            try {
                result = method.invoke(target,args);
                System.out.println("after aop");
            } catch (Exception e){
                System.out.println("execption aop");
            }finally {
                System.out.println("after post aop");
            }

            return result;
        }
    }


}

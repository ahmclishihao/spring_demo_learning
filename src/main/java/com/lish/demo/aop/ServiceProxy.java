package com.lish.demo.aop;

import java.lang.reflect.Method;
import java.math.BigDecimal;

/**
 * 业务代理类
 * @author lish [devlishihao@gmail.com]
 * @date 18-5-8
 */
public class ServiceProxy implements ServiceInterface {

    private ServiceInterface target;

    private ProxyInvocationHandler hanlder;

    public ServiceProxy(ServiceInterface target) {
        this.target = target;
    }

    public ServiceProxy(ProxyInvocationHandler hanlder) {
        this.hanlder = hanlder;
    }

    @Override
    public BigDecimal pay(BigDecimal addMoney) {
        BigDecimal result = null;
        try {
            Method pay = ServiceInterface.class.getMethod("pay", BigDecimal.class);
            result = (BigDecimal) hanlder.invoke(this, pay, new Object[]{addMoney});
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return result;
    }


    interface ProxyInvocationHandler {
        Object invoke(Object proxy, Method method, Object[] args) throws Throwable;
    }

}

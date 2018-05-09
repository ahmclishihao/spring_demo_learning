package com.lish.demo;

import com.lish.demo.aop.ServiceImpl;
import com.lish.demo.aop.ServiceImpl02;
import com.lish.demo.aop.ServiceInterface;
import com.lish.demo.aop.ServiceProxy;

import java.math.BigDecimal;

/**
 * 测试静态代理
 * @author lish [devlishihao@gmail.com]
 * @date 18-5-8
 */
public class TestStaticProxy {

    public static void main(String[] args) {
        ServiceImpl service = new ServiceImpl();
        ServiceImpl02 service02 = new ServiceImpl02();

        ServiceInterface serviceProxy = new ServiceProxy(service);
        ServiceInterface serviceProxy2 = new ServiceProxy(service02);

        serviceProxy.pay(new BigDecimal("1000"));
        serviceProxy2.pay(new BigDecimal("1000"));

    }

}

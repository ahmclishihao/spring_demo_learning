package com.lish.demo;

import com.lish.demo.aop.AliPayInterface;
import com.lish.demo.aop.ServiceImpl02;
import com.lish.demo.aop.ServiceInterface;
import com.lish.demo.repeat.ChildC;

/**
 * 测试重复接口
 * @author lish [devlishihao@gmail.com]
 * @date 18-5-8
 */
public class TestRepeatInterface {

    public static void main(String[] args) {
        ServiceImpl02 serviceImpl02 = new ServiceImpl02();
        ServiceInterface serviceInterface = serviceImpl02;
        AliPayInterface aliPayInterface = serviceImpl02;

        System.out.println(serviceInterface.NAME);
        System.out.println(aliPayInterface.NAME);
        System.out.println(ServiceInterface.SELF);
        System.out.println(ServiceImpl02.SELF);
        // 编译时报错 NAME应用不明确
        // System.out.println(ServiceImpl02.NAME);


        new ChildC();
    }




}

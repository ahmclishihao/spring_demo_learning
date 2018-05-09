package com.lish.demo.aop;

import org.springframework.core.io.ClassPathResource;

import java.math.BigDecimal;

/**
 * 业务实现
 * @author lish [devlishihao@gmail.com]
 * @date 18-5-8
 */
public class ServiceImpl02 implements AliPayInterface,ServiceInterface {

    public BigDecimal pay(BigDecimal addMoney) {
        addMoney.setScale(2);
        System.out.printf("老王买了%s块钱的的苹果 \n",addMoney.toString());
        return addMoney;
    }
}
